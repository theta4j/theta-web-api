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

import org.theta4j.osc.ArrayOption;
import org.theta4j.osc.Option;

import static org.theta4j.osc.Option.SUPPORT;

/**
 * Option definitions for THETA Web API.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/">Overview · Options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class Options {
    public static final Option<Aperture> APERTURE = Option.create("aperture", Aperture.class);
    public static final Option<AutoBracketProgram> AUTO_BRACKET = Option.create("_autoBracket", AutoBracketProgram.class);
    public static final Option<Bitrate> BITRATE = Option.create("_bitrate", Bitrate.class);
    public static final Option<BluetoothPower> BLUETOOTH_POWER = Option.create("_bluetoothPower", BluetoothPower.class);
    public static final Option<Integer> CAPTURE_INTERVAL = Option.create("captureInterval", Integer.class);
    public static final Option<CaptureMode> CAPTURE_MODE = Option.create("captureMode", CaptureMode.class);
    public static final Option<Integer> CAPTURE_NUMBER = Option.create("captureNumber", Integer.class);
    public static final Option<ApiVersion> CLIENT_VERSION = Option.create("clientVersion", ApiVersion.class);
    public static final Option<Integer> COLOR_TEMPERATURE = Option.create("_colorTemperature", Integer.class);
    public static final Option<Integer> COMPOSITE_SHOOTING_OUTPUT_INTERVAL = Option.create("_compositeShootingOutputInterval", Integer.class);
    public static final Option<Integer> COMPOSITE_SHOOTING_TIME = Option.create("_compositeShootingTime", Integer.class);
    public static final Option<String> DATE_TIME_ZONE = Option.create("dateTimeZone", String.class);
    public static final Option<ExposureCompensation> EXPOSURE_COMPENSATION = Option.create("exposureCompensation", ExposureCompensation.class);
    public static final Option<Integer> EXPOSURE_DELAY = Option.create("exposureDelay", Integer.class);
    public static final Option<ExposureProgram> EXPOSURE_PROGRAM = Option.create("exposureProgram", ExposureProgram.class);
    public static final Option<FileFormat> FILE_FORMAT = Option.create("fileFormat", FileFormat.class);
    public static final Option<Filter> FILTER = Option.create("_filter", Filter.class);
    public static final Option<MicrophoneGain> MICROPHONE_GAIN = Option.create("_gain", MicrophoneGain.class);
    public static final Option<GpsInfo> GPS_INFORMATION = Option.create("gpsInfo", GpsInfo.class);
    public static final Option<HDMIResolution> HDMI_RESOLUTION = Option.create("_HDMIreso", HDMIResolution.class);
    public static final Option<ISOSpeed> ISO = Option.create("iso", ISOSpeed.class);
    public static final Option<Integer> LATEST_ENABLED_EXPOSURE_DELAY_TIME = Option.create("_latestEnabledExposureDelayTime", Integer.class);
    public static final Option<Integer> MAX_RECORDABLE_TIME = Option.create("_maxRecordableTime", Integer.class);
    public static final Option<Microphone> MICROPHONE = Option.create("_microphone", Microphone.class);
    public static final Option<MicrophoneChannel> MICROPHONE_CHANNEL = Option.create("_microphoneChannel", MicrophoneChannel.class);
    public static final Option<NetworkType> NETWORK_TYPE = Option.create("_networkType", NetworkType.class);
    public static final Option<Integer> OFF_DELAY = Option.create("offDelay", Integer.class);
    public static final Option<String> PASSWORD = Option.create("_password", String.class);
    public static final Option<PreviewFormat> PREVIEW_FORMAT = Option.create("previewFormat", PreviewFormat.class);
    public static final Option<Integer> REMAINING_PICTURES = Option.create("remainingPictures", Integer.class);
    public static final Option<Long> REMAINING_SPACE = Option.create("remainingSpace", Long.class);
    public static final Option<Integer> REMAINING_VIDEO_SECONDS = Option.create("remainingVideoSeconds", Integer.class);
    public static final Option<ShutterSpeed> SHUTTER_SPEED = Option.create("shutterSpeed", ShutterSpeed.class);
    public static final Option<Integer> SHUTTER_VOLUME = Option.create("_shutterVolume", Integer.class);
    public static final Option<Integer> SLEEP_DELAY = Option.create("sleepDelay", Integer.class);
    public static final Option<Integer> TOTAL_SPACE = Option.create("totalSpace", Integer.class);
    public static final Option<String> USERNAME = Option.create("_username", String.class);
    public static final Option<VideoStitching> VIDEO_STITCHING = Option.create("videoStitching", VideoStitching.class);
    public static final Option<WhiteBalance> WHITE_BALANCE = Option.create("whiteBalance", WhiteBalance.class);
    public static final Option<WlanChannel> WLAN_CHANNEL = Option.create("_wlanChannel", WlanChannel.class);
    public static final Option<WlanFrequency> WLAN_FREQUENCY = Option.create("_wlanFrequency", WlanFrequency.class);

    public static final ArrayOption<Aperture> APERTURE_SUPPORT = ArrayOption.create(APERTURE.getName() + SUPPORT, Aperture.class);
    public static final Option<BracketNumberSupport> BRACKET_NUMBER_SUPPORT = Option.create("_bracketNumber" + SUPPORT, BracketNumberSupport.class);
    public static final ArrayOption<Bitrate> BITRATE_SUPPORT = ArrayOption.create(BITRATE.getName() + SUPPORT, Bitrate.class);
    public static final ArrayOption<BluetoothPower> BLUETOOTH_POWER_SUPPORT = ArrayOption.create(BLUETOOTH_POWER.getName() + SUPPORT, BluetoothPower.class);
    public static final Option<CaptureIntervalSupport> CAPTURE_INTERVAL_SUPPORT = Option.create(CAPTURE_INTERVAL.getName() + SUPPORT, CaptureIntervalSupport.class);
    public static final ArrayOption<CaptureMode> CAPTURE_MODE_SUPPORT = ArrayOption.create(CAPTURE_MODE.getName() + SUPPORT, CaptureMode.class);
    public static final Option<CaptureNumberSupport> CAPTURE_NUMBER_SUPPORT = Option.create(CAPTURE_NUMBER.getName() + SUPPORT, CaptureNumberSupport.class);
    public static final Option<ColorTemperatureSupport> COLOR_TEMPERATURE_SUPPORT = Option.create(COLOR_TEMPERATURE.getName() + SUPPORT, ColorTemperatureSupport.class);
    public static final ArrayOption<Integer> COMPOSITE_SHOOTING_OUTPUT_INTERVAL_SUPPORT = ArrayOption.create(COMPOSITE_SHOOTING_OUTPUT_INTERVAL.getName() + SUPPORT, Integer.class);
    public static final ArrayOption<Integer> COMPOSITE_SHOOTING_TIME_SUPPORT = ArrayOption.create(COMPOSITE_SHOOTING_TIME.getName() + SUPPORT, Integer.class);
    public static final ArrayOption<ExposureCompensation> EXPOSURE_COMPENSATION_SUPPORT = ArrayOption.create(EXPOSURE_COMPENSATION.getName() + SUPPORT, ExposureCompensation.class);
    public static final ArrayOption<Integer> EXPOSURE_DELAY_SUPPORT = ArrayOption.create(EXPOSURE_DELAY.getName() + SUPPORT, Integer.class);
    public static final ArrayOption<ExposureProgram> EXPOSURE_PROGRAM_SUPPORT = ArrayOption.create(EXPOSURE_PROGRAM.getName() + SUPPORT, ExposureProgram.class);
    public static final ArrayOption<FileFormat> FILE_FORMAT_SUPPORT = ArrayOption.create(FILE_FORMAT.getName() + SUPPORT, FileFormat.class);
    public static final ArrayOption<Filter> FILTER_SUPPORT = ArrayOption.create(FILTER.getName() + SUPPORT, Filter.class);
    public static final ArrayOption<MicrophoneGain> MICROPHONE_GAIN_SUPPORT = ArrayOption.create(MICROPHONE_GAIN.getName() + SUPPORT, MicrophoneGain.class);
    public static final ArrayOption<HDMIResolution> HDMI_RESOLUTION_SUPPORT = ArrayOption.create(HDMI_RESOLUTION.getName() + SUPPORT, HDMIResolution.class);
    public static final ArrayOption<ISOSpeed> ISO_SUPPORT = ArrayOption.create(ISO.getName() + SUPPORT, ISOSpeed.class);
    public static final ArrayOption<Integer> MAX_RECORDABLE_TIME_SUPPORT = ArrayOption.create(MAX_RECORDABLE_TIME.getName() + SUPPORT, Integer.class);
    public static final ArrayOption<Microphone> MICROPHONE_SUPPORT = ArrayOption.create(MICROPHONE.getName() + SUPPORT, Microphone.class);
    public static final ArrayOption<MicrophoneChannel> MICROPHONE_CHANNEL_SUPPORT = ArrayOption.create(MICROPHONE_CHANNEL.getName() + SUPPORT, MicrophoneChannel.class);
    public static final ArrayOption<NetworkType> NETWORK_TYPE_SUPPORT = ArrayOption.create(NETWORK_TYPE.getName() + SUPPORT, NetworkType.class);
    public static final ArrayOption<Integer> OFF_DELAY_SUPPORT = ArrayOption.create(OFF_DELAY.getName() + SUPPORT, Integer.class);
    public static final ArrayOption<PreviewFormat> PREVIEW_FORMAT_SUPPORT = ArrayOption.create(PREVIEW_FORMAT.getName() + SUPPORT, PreviewFormat.class);
    public static final ArrayOption<ShutterSpeed> SHUTTER_SPEED_SUPPORT = ArrayOption.create(SHUTTER_SPEED.getName() + SUPPORT, ShutterSpeed.class);
    public static final Option<ShutterVolumeSupport> SHUTTER_VOLUME_SUPPORT = Option.create(SHUTTER_VOLUME.getName() + SUPPORT, ShutterVolumeSupport.class);
    public static final ArrayOption<Integer> SLEEP_DELAY_SUPPORT = ArrayOption.create(SLEEP_DELAY.getName() + SUPPORT, Integer.class);
    public static final ArrayOption<VideoStitching> VIDEO_STITCHING_SUPPORT = ArrayOption.create(VIDEO_STITCHING.getName() + SUPPORT, VideoStitching.class);
    public static final ArrayOption<WhiteBalance> WHITE_BALANCE_SUPPORT = ArrayOption.create(WHITE_BALANCE.getName() + SUPPORT, WhiteBalance.class);
    public static final ArrayOption<WlanFrequency> WLAN_FREQUENCY_SUPPORT = ArrayOption.create(WLAN_FREQUENCY.getName() + SUPPORT, WlanFrequency.class);

    private Options() {
        throw new AssertionError();
    }
}
