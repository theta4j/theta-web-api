/*
 * Copyright (C) 2022 theta4j project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theta4j.osc

import com.burgstaller.okhttp.AuthenticationCacheInterceptor
import com.burgstaller.okhttp.CachingAuthenticatorDecorator
import com.burgstaller.okhttp.digest.CachingAuthenticator
import com.burgstaller.okhttp.digest.Credentials
import com.burgstaller.okhttp.digest.DigestAuthenticator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

class OSCClient private constructor(
        private val endpoint: String,
        private val httpClient: OkHttpClient,
        private val streamHttpClient: OkHttpClient,
) {
    companion object {
        private val GET_OPTIONS_COMMAND = Command.create("camera.getOptions", GetOptions.Parameter.serializer(), GetOptions.Result.serializer())
        private val SET_OPTIONS_COMMAND = Command.create("camera.setOptions", SetOptions.Parameter.serializer(), Unit.serializer())

        private val format = Json { ignoreUnknownKeys = true }

        fun create(endpoint: String): OSCClient {
            val httpClient = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor(logger::debug).setLevel(HttpLoggingInterceptor.Level.BODY))
            }.build()
            val streamHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor(logger::debug).setLevel(HttpLoggingInterceptor.Level.HEADERS))
            }.build()
            return OSCClient(endpoint, httpClient, streamHttpClient)
        }

        fun createWithDigestAuthentication(endpoint: String, username: String, password: String): OSCClient {
            val credentials = Credentials(username, password)
            val authenticator = DigestAuthenticator(credentials)
            val authCache = ConcurrentHashMap<String, CachingAuthenticator>()
            val httpClient = OkHttpClient.Builder().apply {
                authenticator(CachingAuthenticatorDecorator(authenticator, authCache))
                addInterceptor(AuthenticationCacheInterceptor(authCache))
                addInterceptor(HttpLoggingInterceptor(logger::debug).setLevel(HttpLoggingInterceptor.Level.BODY))
            }.build()
            val streamHttpClient = OkHttpClient.Builder().apply {
                authenticator(CachingAuthenticatorDecorator(authenticator, authCache))
                addInterceptor(AuthenticationCacheInterceptor(authCache))
                addInterceptor(HttpLoggingInterceptor(logger::debug).setLevel(HttpLoggingInterceptor.Level.HEADERS))
            }.build()
            return OSCClient(endpoint, httpClient, streamHttpClient)
        }
    }

    suspend fun <T> info(serializer: KSerializer<T>): T = format.decodeFromJsonElement(serializer, httpGet("/osc/info"))

    suspend fun <T> state(serializer: KSerializer<T>): OSCState<T> = OSCState.decode(serializer, httpPost("/osc/state"))

    suspend fun checkForUpdates(fingerprint: String): String {
        val reqBody = buildJsonObject { put("stateFingerprint", fingerprint) }
        val response = httpPost("/osc/checkForUpdates", reqBody)
        response["error"]?.let { throw format.decodeFromJsonElement<OSCException>(it) }
        return response["stateFingerprint"]!!.jsonPrimitive.content
    }

    suspend fun <P, R> commandExecute(command: Command<P, R>, parameters: P? = null): CommandResponse<R> {
        val reqBody = buildJsonObject {
            put("name", command.name)
            if (parameters != null) {
                put("parameters", Json.encodeToJsonElement(command.parameterSerializer, parameters))
            }
        }
        val resBody = httpPost("/osc/commands/execute", reqBody)
        val response = CommandResponse.decode(command.resultSerializer, resBody)
        response.error?.let { throw it }
        return response
    }

    suspend fun <R> commandStatus(response: CommandResponse<R>): CommandResponse<R> {
        val reqBody = buildJsonObject {
            put("id", response.id)
        }
        val resBody = httpPost("/osc/commands/status", reqBody)
        val newResponse = CommandResponse.decode(response.resultSerializer, resBody)
        newResponse.error?.let { throw it }
        return newResponse
    }

    suspend fun <R> await(response: CommandResponse<R>, interval: Long = 100): CommandResponse<R> {
        var res = response
        while (res.state == CommandState.IN_PROGRESS) {
            res = commandStatus(res)
            delay(interval)
        }
        return res
    }

    suspend fun <T> getOption(option: Option<T>): T = getOptions(option)[option]!!

    suspend fun <T> getOption(option: ArrayOption<T>): List<T> = getOptions(option)[option]!!

    suspend fun getOptions(vararg options: Option<*>): OptionSet = getOptions(listOf(*options))

    suspend fun getOptions(options: Collection<Option<*>>): OptionSet {
        require(options.isNotEmpty()) { "options must have 1 or more entries." }
        val optionNames = options.map { it.name }
        val parameter = GetOptions.Parameter(optionNames)
        val response = commandExecute(GET_OPTIONS_COMMAND, parameter)
        response.error?.let { throw it }
        return response.result!!.options
    }

    suspend fun <T> setOption(option: Option<T>, value: T) {
        setOptions {
            put(option, value)
        }
    }

    suspend fun setOptions(optionSet: OptionSet) {
        val parameter = SetOptions.Parameter(optionSet)
        val response = commandExecute(SET_OPTIONS_COMMAND, parameter)
        response.error?.let { throw it }
    }

    suspend fun setOptions(block: OptionSetBuilder.() -> Unit) {
        val optionSet = OptionSetBuilder().apply(block).build()
        setOptions(optionSet)
    }

    suspend fun getLivePreview(): MJpegInputStream {
        val reqBody = buildJsonObject {
            put("name", "camera.getLivePreview")
        }
        val contentType = "application/json; charset=UTF-8".toMediaType()
        val requestBody = Json.encodeToString(reqBody).toRequestBody(contentType)
        val request = Request.Builder()
                .url("$endpoint/osc/commands/execute")
                .post(requestBody)
                .addHeader("Accept", "application/json")
                .addHeader("X-XSRF-Protected", "1")
                .build()
        val response = withContext(Dispatchers.IO) { streamHttpClient.newCall(request).execute() }
        if (response.code == 401) throw IOException(response.message)
        if (response.code != 200) {
            val resBody = withContext(Dispatchers.IO) { response.body!!.string() }
            val jsonObject = format.decodeFromString(JsonObject.serializer(), resBody)
            val commandRes = CommandResponse.decode(Unit.serializer(), jsonObject)
            throw commandRes.error!!
        }
        return MJpegInputStream("---osclivepreview---", response.body!!.byteStream())
    }

    //
    // Helpers
    //

    private suspend fun httpGet(path: String): JsonObject {
        val request = Request.Builder()
                .url(endpoint + path)
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("X-XSRF-Protected", "1")
                .build()
        val response = withContext(Dispatchers.IO) { httpClient.newCall(request).execute() }
        if (response.code == 401) throw IOException(response.message)
        val resBody = withContext(Dispatchers.IO) { response.body!!.string() }
        return Json.decodeFromString(JsonObject.serializer(), resBody)
    }

    private suspend fun httpPost(path: String, body: JsonObject? = null): JsonObject {
        val contentType = "application/json; charset=UTF-8".toMediaType()
        val requestBody = Json.encodeToString(body).toRequestBody(contentType)
        val request = Request.Builder()
                .url(endpoint + path)
                .post(requestBody)
                .addHeader("Accept", "application/json")
                .addHeader("X-XSRF-Protected", "1")
                .build()
        val response = withContext(Dispatchers.IO) { httpClient.newCall(request).execute() }
        if (response.code == 401) throw IOException(response.message)
        val resBody = withContext(Dispatchers.IO) { response.body!!.string() }
        return Json.decodeFromString(JsonObject.serializer(), resBody)
    }
}
