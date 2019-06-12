/*
 * Copyright (C) 2019 theta4j project
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

package org.theta4j.webapi;

import org.theta4j.osc.Command;
import org.theta4j.osc.OptionSet;

final class Commands {
    static final Command<Void, Void> FINISH_WLAN = Command.create("camera._finishWlan", Void.class, Void.class);

    static final Command<Void, TakePicture.Result> TAKE_PICTURE = Command.create("camera.takePicture", Void.class, TakePicture.Result.class);

    static final Command<StartCapture.Parameter, StartCapture.Result> START_CAPTURE = Command.create("camera.startCapture", StartCapture.Parameter.class, StartCapture.Result.class);

    static final Command<Void, StopCapture.Result> STOP_CAPTURE = Command.create("camera.stopCapture", Void.class, StopCapture.Result.class);

    static final Command<ListFiles.Parameter, ListFiles.Result> LIST_FILES = Command.create("camera.listFiles", ListFiles.Parameter.class, ListFiles.Result.class);

    static final Command<Delete.Parameter, Void> DELETE = Command.create("camera.delete", Delete.Parameter.class, Void.class);

    static final Command<GetMetadata.Parameter, Metadata> GET_METADATA = Command.create("camera._getMetadata", GetMetadata.Parameter.class, Metadata.class);

    static final Command<Void, Void> RESET = Command.create("camera.reset", Void.class, Void.class);

    static final Command<GetMySetting.Parameter, OptionSet> GET_MY_SETTINGS = Command.create("camera._getMySetting", GetMySetting.Parameter.class, OptionSet.class);

    static final Command<SetMySetting.Parameter, Void> SET_MY_SETTINGS = Command.create("camera._setMySetting", SetMySetting.Parameter.class, Void.class);

    static final Command<DeleteMySetting.Parameter, Void> DELETE_MY_SETTING = Command.create("camera._deleteMySetting", DeleteMySetting.Parameter.class, Void.class);

    static final Command<Void, Void> STOP_SELF_TIMER = Command.create("camera._stopSelfTimer", Void.class, Void.class);

    static final Command<ConvertVideoFormats.Parameter, ConvertVideoFormats.Result> CONVERT_VIDEO_FORMATS = Command.create("camera._convertVideoFormats", ConvertVideoFormats.Parameter.class, ConvertVideoFormats.Result.class);

    static final Command<Void, Void> CANCEL_VIDEO_CONVERT = Command.create("camera._cancelVideoConvert", Void.class, Void.class);

    static final Command<SetBluetoothDevice.Parameter, SetBluetoothDevice.Result> SET_BLUETOOTH_DEVICE = Command.create("camera._setBluetoothDevice", SetBluetoothDevice.Parameter.class, SetBluetoothDevice.Result.class);

    static final Command<Void, ListAccessPoints.Result> LIST_ACCESS_POINTS = Command.create("camera._listAccessPoints", Void.class, ListAccessPoints.Result.class);

    static final Command<AccessPoint, Void> SET_ACCESS_POINT = Command.create("camera._setAccessPoint", AccessPoint.class, Void.class);

    static final Command<DeleteAccessPoint.Parameter, Void> DELETE_ACCESS_POINT = Command.create("camera._deleteAccessPoint", DeleteAccessPoint.Parameter.class, Void.class);

    static final Command<Void, ListPlugins.Result> LIST_PLUGINS = Command.create("camera._listPlugins", Void.class, ListPlugins.Result.class);

    static final Command<SetPlugin.Parameter, Void> SET_PLUGIN = Command.create("camera._setPlugin", SetPlugin.Parameter.class, Void.class);

    static final Command<PluginControl.Parameter, Void> PLUGIN_CONTROL = Command.create("camera._pluginControl", PluginControl.Parameter.class, Void.class);

    static final Command<Void, GetPluginOrders.Result> GET_PLUGIN_ORDERS = Command.create("camera._getPluginOrders", Void.class, GetPluginOrders.Result.class);

    static final Command<SetPluginOrders.Parameter, Void> SET_PLUGIN_ORDERS = Command.create("camera._setPluginOrders", SetPluginOrders.Parameter.class, Void.class);

    private Commands() {
        throw new AssertionError();
    }
}
