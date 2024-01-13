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
import org.theta4j.osc.ArrayOption
import org.theta4j.osc.Option
import org.theta4j.osc.Option.Companion.SUPPORT

object Options {
    val AI_AUTO_THUMBNAIL = Option.create("_aiAutoThumbnail", AIAutoThumbnail.serializer())
    val APERTURE = Option.create("aperture", Aperture.serializer())
    val AUTHENTICATION = Option.create("_authentication", Authentication.serializer())
    val AUTO_BRACKET = Option.create("_autoBracket", AutoBracketProgram.serializer())
    val BITRATE = Option.create("_bitrate", Bitrate.serializer())
    val BLUETOOTH_POWER = Option.create("_bluetoothPower", BluetoothPower.serializer())
    val BLUETOOTH_CLASSIC_ENABLE = Option.create("_bluetoothClassicEnable", Boolean.serializer())
    val BLUETOOTH_ROLE = Option.create("_bluetoothRole", BluetoothRole.serializer())
    val CAPTURE_INTERVAL = Option.create("captureInterval", Int.serializer())
    val CAPTURE_MODE = Option.create("captureMode", CaptureMode.serializer())
    val CAMERA_MODE = Option.create("_cameraMode", CameraMode.serializer())
    val CAMERA_CONTROL_SOURCE = Option.create("_cameraControlSource", CameraControlSource.serializer())
    val CAPTURE_NUMBER = Option.create("captureNumber", Int.serializer())
    val CLIENT_VERSION = Option.create("clientVersion", ApiVersion.serializer())
    val COLOR_TEMPERATURE = Option.create("_colorTemperature", Int.serializer())
    val COMPOSITE_SHOOTING_OUTPUT_INTERVAL = Option.create("_compositeShootingOutputInterval", Int.serializer())
    val COMPOSITE_SHOOTING_TIME = Option.create("_compositeShootingTime", Int.serializer())
    val CONTINUOUS_NUMBER = Option.create("continuousNumber", Int.serializer())
    val DATE_TIME_ZONE = Option.create("dateTimeZone", DateTimeZoneSerializer)
    val EXPOSURE_COMPENSATION = Option.create("exposureCompensation", ExposureCompensation.serializer())
    val EXPOSURE_DELAY = Option.create("exposureDelay", Int.serializer())
    val EXPOSURE_PROGRAM = Option.create("exposureProgram", ExposureProgram.serializer())
    val FACE_DETECT = Option.create("_faceDetect", FaceDetect.serializer())
    val FILE_FORMAT = Option.create("fileFormat", FileFormat.serializer())
    val FILTER = Option.create("_filter", Filter.serializer())
    val FUNCTION = Option.create("_function", Function.serializer())
    val MICROPHONE_GAIN = Option.create("_gain", MicrophoneGain.serializer())
    val GPS_INFORMATION = Option.create("gpsInfo", GpsInfo.serializer())
    val GPS_TAG_RECORDING = Option.create("_gpsTagRecording", GPSTagRecording.serializer())
    val HDMI_RESOLUTION = Option.create("_HDMIreso", HDMIResolution.serializer())
    val IMAGE_STITCHING = Option.create("_imageStitching", ImageStitching.serializer())
    val ISO = Option.create("iso", Iso.serializer())
    val ISO_AUTO_HIGH_LIMIT = Option.create("isoAutoHighLimit", Iso.serializer())
    val LANGUAGE = Option.create("_language", String.serializer())
    val LATEST_ENABLED_EXPOSURE_DELAY_TIME = Option.create("_latestEnabledExposureDelayTime", Int.serializer())
    val MAX_RECORDABLE_TIME = Option.create("_maxRecordableTime", Int.serializer())
    val MICROPHONE = Option.create("_microphone", Microphone.serializer())
    val MICROPHONE_CHANNEL = Option.create("_microphoneChannel", MicrophoneChannel.serializer())
    val NETWORK_TYPE = Option.create("_networkType", NetworkType.serializer())
    val OFF_DELAY = Option.create("offDelay", Int.serializer())
    val POWER_SAVING = Option.create("_powerSaving", PowerSaving.serializer())
    val PASSWORD = Option.create("_password", String.serializer())
    val PREVIEW_FORMAT = Option.create("previewFormat", PreviewFormat.serializer())
    val REMAINING_PICTURES = Option.create("remainingPictures", Int.serializer())
    val REMAINING_SPACE = Option.create("remainingSpace", Long.serializer())
    val REMAINING_VIDEO_SECONDS = Option.create("remainingVideoSeconds", Int.serializer())
    val SHOOTING_METHOD = Option.create("_shootingMethod", ShootingMethod.serializer())
    val SHUTTER_SPEED = Option.create("shutterSpeed", ShutterSpeed.serializer())
    val SHUTTER_VOLUME = Option.create("_shutterVolume", Int.serializer())
    val SLEEP_DELAY = Option.create("sleepDelay", Int.serializer())
    val TIME_SHIFT = Option.create("_timeShift", TimeShift.serializer())
    val TOP_BOTTOM_CORRECTION = Option.create("_topBottomCorrection", TopBottomCorrection.serializer())
    val TOP_BOTTOM_CORRECTION_ROTATION = Option.create("_topBottomCorrectionRotation", TopBottomCorrectionRotation.serializer())
    val TOTAL_SPACE = Option.create("totalSpace", Int.serializer())
    val USERNAME = Option.create("_username", String.serializer())
    val VIDEO_STITCHING = Option.create("videoStitching", VideoStitching.serializer())
    val VISIBILITY_REDUCTION = Option.create("_visibilityReduction", VisibilityReduction.serializer())
    val WHITE_BALANCE = Option.create("whiteBalance", WhiteBalance.serializer())
    val WLAN_CHANNEL = Option.create("_wlanChannel", WlanChannel.serializer())
    val WLAN_FREQUENCY = Option.create("_wlanFrequency", WlanFrequency.serializer())

    val AI_AUTO_THUMBNAIL_SUPPORT = ArrayOption.create(AI_AUTO_THUMBNAIL.name + SUPPORT, AIAutoThumbnail.serializer())
    val APERTURE_SUPPORT = ArrayOption.create(APERTURE.name + SUPPORT, Aperture.serializer())
    val AUTHENTICATION_SUPPORT = ArrayOption.create(AUTHENTICATION.name + SUPPORT, Authentication.serializer())
    val BRACKET_NUMBER_SUPPORT = Option.create("_bracketNumber$SUPPORT", BracketNumberSupport.serializer())
    val BITRATE_SUPPORT = ArrayOption.create(BITRATE.name + SUPPORT, Bitrate.serializer())
    val BLUETOOTH_POWER_SUPPORT = ArrayOption.create(BLUETOOTH_POWER.name + SUPPORT, BluetoothPower.serializer())
    val CAPTURE_INTERVAL_SUPPORT = Option.create(CAPTURE_INTERVAL.name + SUPPORT, CaptureIntervalSupport.serializer())
    val CAPTURE_MODE_SUPPORT = ArrayOption.create(CAPTURE_MODE.name + SUPPORT, CaptureMode.serializer())
    val CAMERA_MODE_SUPPORT = ArrayOption.create(CAMERA_MODE.name + SUPPORT, CameraMode.serializer())
    val CAMERA_CONTROL_SOURCE_SUPPORT = ArrayOption.create(CAMERA_CONTROL_SOURCE.name + SUPPORT, CameraControlSource.serializer())
    val CAPTURE_NUMBER_SUPPORT = Option.create(CAPTURE_NUMBER.name + SUPPORT, CaptureNumberSupport.serializer())
    val COLOR_TEMPERATURE_SUPPORT = Option.create(COLOR_TEMPERATURE.name + SUPPORT, ColorTemperatureSupport.serializer())
    val COMPOSITE_SHOOTING_OUTPUT_INTERVAL_SUPPORT = ArrayOption.create(COMPOSITE_SHOOTING_OUTPUT_INTERVAL.name + SUPPORT, Int.serializer())
    val COMPOSITE_SHOOTING_TIME_SUPPORT = ArrayOption.create(COMPOSITE_SHOOTING_TIME.name + SUPPORT, Int.serializer())
    val CONTINUOUS_NUMBER_SUPPORT = Option.create(CONTINUOUS_NUMBER.name + SUPPORT, Int.serializer())
    val EXPOSURE_COMPENSATION_SUPPORT = ArrayOption.create(EXPOSURE_COMPENSATION.name + SUPPORT, ExposureCompensation.serializer())
    val EXPOSURE_DELAY_SUPPORT = ArrayOption.create(EXPOSURE_DELAY.name + SUPPORT, Int.serializer())
    val EXPOSURE_PROGRAM_SUPPORT = ArrayOption.create(EXPOSURE_PROGRAM.name + SUPPORT, ExposureProgram.serializer())
    val FACE_DETECT_SUPPORT = ArrayOption.create(FACE_DETECT.name + SUPPORT, FaceDetect.serializer())
    val FILE_FORMAT_SUPPORT = ArrayOption.create(FILE_FORMAT.name + SUPPORT, FileFormat.serializer())
    val FILTER_SUPPORT = ArrayOption.create(FILTER.name + SUPPORT, Filter.serializer())
    val FUNCTION_SUPPORT = ArrayOption.create(FUNCTION.name + SUPPORT, Function.serializer())
    val MICROPHONE_GAIN_SUPPORT = ArrayOption.create(MICROPHONE_GAIN.name + SUPPORT, MicrophoneGain.serializer())
    val GPS_TAG_RECORDING_SUPPORT = ArrayOption.create(GPS_TAG_RECORDING.name + SUPPORT, GPSTagRecording.serializer())
    val HDMI_RESOLUTION_SUPPORT = ArrayOption.create(HDMI_RESOLUTION.name + SUPPORT, HDMIResolution.serializer())
    val IMAGE_STITCHING_SUPPORT = ArrayOption.create(IMAGE_STITCHING.name + SUPPORT, ImageStitching.serializer())
    val ISO_SUPPORT = ArrayOption.create(ISO.name + SUPPORT, Iso.serializer())
    val ISO_AUTO_HIGH_LIMIT_SUPPORT = ArrayOption.create(ISO_AUTO_HIGH_LIMIT.name + SUPPORT, Iso.serializer())
    val LANGUAGE_SUPPORT = ArrayOption.create(LANGUAGE.name + SUPPORT, String.serializer())
    val MAX_RECORDABLE_TIME_SUPPORT = ArrayOption.create(MAX_RECORDABLE_TIME.name + SUPPORT, Int.serializer())
    val MICROPHONE_SUPPORT = ArrayOption.create(MICROPHONE.name + SUPPORT, Microphone.serializer())
    val MICROPHONE_CHANNEL_SUPPORT = ArrayOption.create(MICROPHONE_CHANNEL.name + SUPPORT, MicrophoneChannel.serializer())
    val NETWORK_TYPE_SUPPORT = ArrayOption.create(NETWORK_TYPE.name + SUPPORT, NetworkType.serializer())
    val OFF_DELAY_SUPPORT = ArrayOption.create(OFF_DELAY.name + SUPPORT, Int.serializer())
    val POWER_SAVING_SUPPORT = ArrayOption.create(POWER_SAVING.name + SUPPORT, PowerSaving.serializer())
    val PREVIEW_FORMAT_SUPPORT = ArrayOption.create(PREVIEW_FORMAT.name + SUPPORT, PreviewFormat.serializer())
    val SHOOTING_METHOD_SUPPORT = ArrayOption.create(SHOOTING_METHOD.name + SUPPORT, ShootingMethod.serializer())
    val SHUTTER_SPEED_SUPPORT = ArrayOption.create(SHUTTER_SPEED.name + SUPPORT, ShutterSpeed.serializer())
    val SHUTTER_VOLUME_SUPPORT = Option.create(SHUTTER_VOLUME.name + SUPPORT, ShutterVolumeSupport.serializer())
    val SLEEP_DELAY_SUPPORT = ArrayOption.create(SLEEP_DELAY.name + SUPPORT, Int.serializer())
    val TIME_SHIFT_SUPPORT = Option.create(TIME_SHIFT.name + SUPPORT, TimeShiftSupport.serializer())
    val TOP_BOTTOM_CORRECTION_SUPPORT = ArrayOption.create(TOP_BOTTOM_CORRECTION.name + SUPPORT, TopBottomCorrection.serializer())
    val TOP_BOTTOM_CORRECTION_ROTATION_SUPPORT = Option.create(TOP_BOTTOM_CORRECTION_ROTATION.name + SUPPORT, TopBottomCorrectionRotationSupport.serializer())
    val VIDEO_STITCHING_SUPPORT = ArrayOption.create(VIDEO_STITCHING.name + SUPPORT, VideoStitching.serializer())
    val WHITE_BALANCE_SUPPORT = ArrayOption.create(WHITE_BALANCE.name + SUPPORT, WhiteBalance.serializer())
    val WLAN_FREQUENCY_SUPPORT = ArrayOption.create(WLAN_FREQUENCY.name + SUPPORT, WlanFrequency.serializer())
}
