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

package org.theta4j.webapi

import org.theta4j.osc.*
import org.theta4j.osc.OSCClient.Companion.createWithDigestAuthentication
import java.net.URI

class Theta private constructor(private val oscClient: OSCClient) {
    companion object {
        private const val DEFAULT_ENDPOINT = "http://192.168.1.1"

        private const val INTERNAL_ENDPOINT = "http:127.0.0.1:8080"

        fun create(): Theta = create(DEFAULT_ENDPOINT)

        fun create(endpoint: String): Theta = Theta(OSCClient.create(endpoint))

        fun create(endpoint: String, username: String, password: String): Theta {
            val oscClient = createWithDigestAuthentication(endpoint, username, password)
            return Theta(oscClient)
        }

        fun createForPlugin(): Theta = Theta(OSCClient.create(INTERNAL_ENDPOINT))
    }

    suspend fun info(): ThetaInfo = oscClient.info(ThetaInfo.serializer())

    suspend fun state(): OSCState<ThetaState> = oscClient.state(ThetaState.serializer())

    suspend fun checkForUpdates(fingerprint: String): String = oscClient.checkForUpdates(fingerprint)

    suspend fun <R> commandStatus(response: CommandResponse<R>): CommandResponse<R> = oscClient.commandStatus(response)

    suspend fun <R> await(response: CommandResponse<R>): CommandResponse<R> = oscClient.await(response)

    suspend fun <T> getOption(option: Option<T>): T = oscClient.getOption(option)

    suspend fun <T> getOption(option: ArrayOption<T>): List<T> = oscClient.getOption(option)

    suspend fun getOptions(vararg options: Option<*>): OptionSet = oscClient.getOptions(*options)

    suspend fun getOptions(options: Collection<Option<*>>): OptionSet = oscClient.getOptions(options)

    suspend fun <T> setOption(option: Option<T>, value: T) {
        oscClient.setOption(option, value)
    }

    suspend fun setOptions(optionSet: OptionSet) {
        oscClient.setOptions(optionSet)
    }

    suspend fun setOptions(block: OptionSetBuilder.() -> Unit) {
        oscClient.setOptions(block)
    }

    suspend fun finishWlan(): CommandResponse<Unit> = oscClient.commandExecute(Commands.FINISH_WLAN)

    suspend fun takePicture(): CommandResponse<TakePicture.Result> = oscClient.commandExecute(Commands.TAKE_PICTURE)

    suspend fun startCapture(): CommandResponse<StartCapture.Result> = oscClient.commandExecute(Commands.START_CAPTURE)

    suspend fun startCapture(captureMode: StartCapture.CaptureMode): CommandResponse<StartCapture.Result> {
        val parameters = StartCapture.Parameter(captureMode)
        return oscClient.commandExecute(Commands.START_CAPTURE, parameters)
    }

    suspend fun stopCapture(): CommandResponse<StopCapture.Result> = oscClient.commandExecute(Commands.STOP_CAPTURE, null)

    suspend fun listFiles(parameter: ListFiles.Parameter): CommandResponse<ListFiles.Result> = oscClient.commandExecute(Commands.LIST_FILES, parameter)

    suspend fun delete(vararg fileUrls: URI): CommandResponse<Unit> = delete(fileUrls.toList())

    suspend fun getLivePreview(): MJpegInputStream = oscClient.getLivePreview()

    suspend fun delete(fileUrls: Collection<URI>): CommandResponse<Unit> {
        require(fileUrls.isNotEmpty()) { "fileUrls must have 1 or more entries" }
        val parameter = Delete.Parameter(fileUrls.toList())
        return oscClient.commandExecute(Commands.DELETE, parameter)
    }

    suspend fun getMetadata(fileUrl: URI): CommandResponse<Metadata> {
        val parameter = GetMetadata.Parameter(fileUrl)
        return oscClient.commandExecute(Commands.GET_METADATA, parameter)
    }

    suspend fun reset(): CommandResponse<Unit> = oscClient.commandExecute(Commands.RESET)

    suspend fun getMySetting(captureMode: CaptureMode, vararg options: Option<*>): OptionSet = getMySetting(captureMode, options.toList())

    suspend fun getMySetting(captureMode: CaptureMode, options: Collection<Option<*>>): OptionSet {
        val optionNames = options.map { it.name }
        val parameter = GetMySetting.Parameter(captureMode, optionNames)
        return oscClient.commandExecute(Commands.GET_MY_SETTINGS, parameter).result!!.options
    }

    suspend fun setMySetting(captureMode: CaptureMode, block: OptionSetBuilder.() -> Unit) {
        val optionSet = OptionSetBuilder().apply(block).build()
        setMySetting(captureMode, optionSet)
    }

    suspend fun setMySetting(captureMode: CaptureMode, optionSet: OptionSet) {
        val parameter = SetMySetting.Parameter(captureMode, optionSet)
        oscClient.commandExecute(Commands.SET_MY_SETTINGS, parameter)
    }

    suspend fun deleteMySetting(captureMode: CaptureMode) {
        val parameter = DeleteMySetting.Parameter(captureMode)
        oscClient.commandExecute(Commands.DELETE_MY_SETTING, parameter)
    }

    suspend fun stopSelfTimer(): CommandResponse<Unit> = oscClient.commandExecute(Commands.STOP_SELF_TIMER)

    suspend fun convertVideoFormats(parameter: ConvertVideoFormats.Parameter): CommandResponse<ConvertVideoFormats.Result> = oscClient.commandExecute(Commands.CONVERT_VIDEO_FORMATS, parameter)

    suspend fun cancelVideoConvert(): CommandResponse<Unit> = oscClient.commandExecute(Commands.CANCEL_VIDEO_CONVERT)

    suspend fun setBluetoothDevice(uuid: String): CommandResponse<SetBluetoothDevice.Result> {
        val parameter = SetBluetoothDevice.Parameter(uuid)
        return oscClient.commandExecute(Commands.SET_BLUETOOTH_DEVICE, parameter)
    }

    suspend fun listAccessPoints(): CommandResponse<ListAccessPoints.Result> = oscClient.commandExecute(Commands.LIST_ACCESS_POINTS)

    suspend fun setAccessPoint(accessPoint: AccessPoint): CommandResponse<Unit> = oscClient.commandExecute(Commands.SET_ACCESS_POINT, accessPoint)

    suspend fun deleteAccessPoint(ssid: String): CommandResponse<Unit> {
        val parameter = DeleteAccessPoint.Parameter(ssid)
        return oscClient.commandExecute(Commands.DELETE_ACCESS_POINT, parameter)
    }

    suspend fun listPlugins(): CommandResponse<ListPlugins.Result> = oscClient.commandExecute(Commands.LIST_PLUGINS)

    suspend fun setPlugin(packageName: String): CommandResponse<Unit> {
        val parameter = SetPlugin.Parameter(packageName, true)
        return oscClient.commandExecute(Commands.SET_PLUGIN, parameter)
    }

    suspend fun pluginControl(action: PluginAction, packageName: String? = null): CommandResponse<Unit> {
        val parameter = PluginControl.Parameter(action, packageName)
        return oscClient.commandExecute(Commands.PLUGIN_CONTROL, parameter)
    }

    suspend fun getPluginOrders(): CommandResponse<GetPluginOrders.Result> = oscClient.commandExecute(Commands.GET_PLUGIN_ORDERS)

    suspend fun setPluginOrders(packageNames: List<String>): CommandResponse<Unit> {
        val parameter = SetPluginOrders.Parameter(packageNames)
        return oscClient.commandExecute(Commands.SET_PLUGIN_ORDERS, parameter)
    }
}
