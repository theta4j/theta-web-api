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

import kotlinx.serialization.builtins.serializer
import org.theta4j.osc.Command.Companion.create

internal object Commands {
    val FINISH_WLAN = create("camera._finishWlan", Unit.serializer(), Unit.serializer())
    val TAKE_PICTURE = create("camera.takePicture", Unit.serializer(), TakePicture.Result.serializer())
    val START_CAPTURE = create("camera.startCapture", StartCapture.Parameter.serializer(), StartCapture.Result.serializer())
    val STOP_CAPTURE = create("camera.stopCapture", Unit.serializer(), StopCapture.Result.serializer())
    val LIST_FILES = create("camera.listFiles", ListFiles.Parameter.serializer(), ListFiles.Result.serializer())
    val DELETE = create("camera.delete", Delete.Parameter.serializer(), Unit.serializer())
    val GET_METADATA = create("camera._getMetadata", GetMetadata.Parameter.serializer(), Metadata.serializer())
    val RESET = create("camera.reset", Unit.serializer(), Unit.serializer())
    val GET_MY_SETTINGS = create("camera._getMySetting", GetMySetting.Parameter.serializer(), GetMySetting.Result.serializer())
    val SET_MY_SETTINGS = create("camera._setMySetting", SetMySetting.Parameter.serializer(), Unit.serializer())
    val DELETE_MY_SETTING = create("camera._deleteMySetting", DeleteMySetting.Parameter.serializer(), Unit.serializer())
    val STOP_SELF_TIMER = create("camera._stopSelfTimer", Unit.serializer(), Unit.serializer())
    val CONVERT_VIDEO_FORMATS = create("camera._convertVideoFormats", ConvertVideoFormats.Parameter.serializer(), ConvertVideoFormats.Result.serializer())
    val CANCEL_VIDEO_CONVERT = create("camera._cancelVideoConvert", Unit.serializer(), Unit.serializer())
    val SET_BLUETOOTH_DEVICE = create("camera._setBluetoothDevice", SetBluetoothDevice.Parameter.serializer(), SetBluetoothDevice.Result.serializer())
    val LIST_ACCESS_POINTS = create("camera._listAccessPoints", Unit.serializer(), ListAccessPoints.Result.serializer())
    val SET_ACCESS_POINT = create("camera._setAccessPoint", AccessPoint.serializer(), Unit.serializer())
    val DELETE_ACCESS_POINT = create("camera._deleteAccessPoint", DeleteAccessPoint.Parameter.serializer(), Unit.serializer())
    val LIST_PLUGINS = create("camera._listPlugins", Unit.serializer(), ListPlugins.Result.serializer())
    val SET_PLUGIN = create("camera._setPlugin", SetPlugin.Parameter.serializer(), Unit.serializer())
    val PLUGIN_CONTROL = create("camera._pluginControl", PluginControl.Parameter.serializer(), Unit.serializer())
    val GET_PLUGIN_ORDERS = create("camera._getPluginOrders", Unit.serializer(), GetPluginOrders.Result.serializer())
    val SET_PLUGIN_ORDERS = create("camera._setPluginOrders", SetPluginOrders.Parameter.serializer(), Unit.serializer())
}
