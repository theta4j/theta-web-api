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

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive

data class CommandResponse<R> internal constructor(
        val name: String,

        val state: CommandState,

        val id: String?,

        val result: R?,

        internal val resultSerializer: KSerializer<R>,

        internal val error: OSCException?,

        val progress: Progress?,
) {
    @Serializable
    data class Progress(val completion: Double? = null)

    companion object {
        private val format = Json { ignoreUnknownKeys = true }

        internal fun <R> decode(resultSerializer: KSerializer<R>, json: JsonObject): CommandResponse<R> {
            val name = json["name"]!!.jsonPrimitive.content
            val state = format.decodeFromJsonElement<CommandState>(json["state"]!!)
            val newID = json["id"]?.jsonPrimitive?.content
            val result = json["results"]?.let { format.decodeFromJsonElement(resultSerializer, it) }
            val error = json["error"]?.let { format.decodeFromJsonElement<OSCException>(it) }
            val progress = json["progress"]?.let { format.decodeFromJsonElement<Progress>(it) }
            return CommandResponse(name, state, newID, result, resultSerializer, error, progress)
        }
    }
}
