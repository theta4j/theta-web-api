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

import enableDebugLog
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.theta4j.webapi.Options.AI_AUTO_THUMBNAIL
import org.theta4j.webapi.Options.AI_AUTO_THUMBNAIL_SUPPORT
import org.theta4j.webapi.Options.APERTURE
import org.theta4j.webapi.Options.APERTURE_SUPPORT
import org.theta4j.webapi.Options.AUTO_BRACKET
import org.theta4j.webapi.Options.BITRATE
import org.theta4j.webapi.Options.BITRATE_SUPPORT
import org.theta4j.webapi.Options.BLUETOOTH_POWER
import org.theta4j.webapi.Options.BLUETOOTH_POWER_SUPPORT
import org.theta4j.webapi.Options.BRACKET_NUMBER_SUPPORT
import org.theta4j.webapi.Options.CAMERA_CONTROL_SOURCE
import org.theta4j.webapi.Options.CAMERA_CONTROL_SOURCE_SUPPORT
import org.theta4j.webapi.Options.CAMERA_MODE
import org.theta4j.webapi.Options.CAMERA_MODE_SUPPORT
import org.theta4j.webapi.Options.CAPTURE_INTERVAL
import org.theta4j.webapi.Options.CAPTURE_INTERVAL_SUPPORT
import org.theta4j.webapi.Options.CAPTURE_MODE
import org.theta4j.webapi.Options.CAPTURE_MODE_SUPPORT
import org.theta4j.webapi.Options.CAPTURE_NUMBER
import org.theta4j.webapi.Options.CAPTURE_NUMBER_SUPPORT
import org.theta4j.webapi.Options.CLIENT_VERSION
import org.theta4j.webapi.Options.COLOR_TEMPERATURE
import org.theta4j.webapi.Options.COLOR_TEMPERATURE_SUPPORT
import org.theta4j.webapi.Options.CONTINUOUS_NUMBER
import org.theta4j.webapi.Options.CONTINUOUS_NUMBER_SUPPORT
import org.theta4j.webapi.Options.DATE_TIME_ZONE
import org.theta4j.webapi.Options.EXPOSURE_COMPENSATION
import org.theta4j.webapi.Options.EXPOSURE_COMPENSATION_SUPPORT
import org.theta4j.webapi.Options.EXPOSURE_DELAY
import org.theta4j.webapi.Options.EXPOSURE_DELAY_SUPPORT
import org.theta4j.webapi.Options.EXPOSURE_PROGRAM
import org.theta4j.webapi.Options.EXPOSURE_PROGRAM_SUPPORT
import org.theta4j.webapi.Options.FACE_DETECT
import org.theta4j.webapi.Options.FACE_DETECT_SUPPORT
import org.theta4j.webapi.Options.FILE_FORMAT
import org.theta4j.webapi.Options.FILE_FORMAT_SUPPORT
import org.theta4j.webapi.Options.FILTER
import org.theta4j.webapi.Options.FILTER_SUPPORT
import org.theta4j.webapi.Options.FUNCTION
import org.theta4j.webapi.Options.FUNCTION_SUPPORT
import org.theta4j.webapi.Options.GPS_INFORMATION
import org.theta4j.webapi.Options.GPS_TAG_RECORDING
import org.theta4j.webapi.Options.GPS_TAG_RECORDING_SUPPORT
import org.theta4j.webapi.Options.IMAGE_STITCHING
import org.theta4j.webapi.Options.IMAGE_STITCHING_SUPPORT
import org.theta4j.webapi.Options.ISO
import org.theta4j.webapi.Options.ISO_AUTO_HIGH_LIMIT
import org.theta4j.webapi.Options.ISO_AUTO_HIGH_LIMIT_SUPPORT
import org.theta4j.webapi.Options.ISO_SUPPORT
import org.theta4j.webapi.Options.LANGUAGE
import org.theta4j.webapi.Options.LANGUAGE_SUPPORT
import org.theta4j.webapi.Options.LATEST_ENABLED_EXPOSURE_DELAY_TIME
import org.theta4j.webapi.Options.MAX_RECORDABLE_TIME
import org.theta4j.webapi.Options.MAX_RECORDABLE_TIME_SUPPORT
import org.theta4j.webapi.Options.MICROPHONE_GAIN
import org.theta4j.webapi.Options.MICROPHONE_GAIN_SUPPORT
import org.theta4j.webapi.Options.NETWORK_TYPE
import org.theta4j.webapi.Options.OFF_DELAY
import org.theta4j.webapi.Options.OFF_DELAY_SUPPORT
import org.theta4j.webapi.Options.PASSWORD
import org.theta4j.webapi.Options.POWER_SAVING
import org.theta4j.webapi.Options.POWER_SAVING_SUPPORT
import org.theta4j.webapi.Options.PREVIEW_FORMAT
import org.theta4j.webapi.Options.PREVIEW_FORMAT_SUPPORT
import org.theta4j.webapi.Options.REMAINING_PICTURES
import org.theta4j.webapi.Options.REMAINING_SPACE
import org.theta4j.webapi.Options.REMAINING_VIDEO_SECONDS
import org.theta4j.webapi.Options.SHOOTING_METHOD
import org.theta4j.webapi.Options.SHOOTING_METHOD_SUPPORT
import org.theta4j.webapi.Options.SHUTTER_SPEED
import org.theta4j.webapi.Options.SHUTTER_SPEED_SUPPORT
import org.theta4j.webapi.Options.SHUTTER_VOLUME
import org.theta4j.webapi.Options.SHUTTER_VOLUME_SUPPORT
import org.theta4j.webapi.Options.SLEEP_DELAY
import org.theta4j.webapi.Options.SLEEP_DELAY_SUPPORT
import org.theta4j.webapi.Options.TIME_SHIFT
import org.theta4j.webapi.Options.TIME_SHIFT_SUPPORT
import org.theta4j.webapi.Options.TOP_BOTTOM_CORRECTION
import org.theta4j.webapi.Options.TOP_BOTTOM_CORRECTION_ROTATION
import org.theta4j.webapi.Options.TOP_BOTTOM_CORRECTION_ROTATION_SUPPORT
import org.theta4j.webapi.Options.TOP_BOTTOM_CORRECTION_SUPPORT
import org.theta4j.webapi.Options.TOTAL_SPACE
import org.theta4j.webapi.Options.USERNAME
import org.theta4j.webapi.Options.VIDEO_STITCHING
import org.theta4j.webapi.Options.VIDEO_STITCHING_SUPPORT
import org.theta4j.webapi.Options.WHITE_BALANCE
import org.theta4j.webapi.Options.WHITE_BALANCE_SUPPORT
import org.theta4j.webapi.Options.WLAN_FREQUENCY
import org.theta4j.webapi.Options.WLAN_FREQUENCY_SUPPORT
import java.time.OffsetDateTime
import kotlin.properties.Delegates

class OptionTest : FunSpec({
    enableDebugLog()

    val theta = Theta.createForPlugin()

    context("_aiAutoThumbnail") {
        theta.setOption(CAPTURE_MODE, CaptureMode.IMAGE)
        var support by Delegates.notNull<List<AIAutoThumbnail>>()
        test("Get support") {
            support = theta.getOption(AI_AUTO_THUMBNAIL_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(AI_AUTO_THUMBNAIL, value)
                }
                test("Get") {
                    theta.getOption(AI_AUTO_THUMBNAIL) shouldBe value
                }
            }
        }
    }

    context("aperture") {
        var support by Delegates.notNull<List<Aperture>>()
        test("Get support") {
            support = theta.getOption(APERTURE_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(APERTURE, value)
                }
                test("Get") {
                    theta.getOption(APERTURE) shouldBe value
                }
            }
        }
    }

    context("_autoBracket") {
        val numbersList = theta.getOption(BRACKET_NUMBER_SUPPORT)
        for (bracketNumber in numbersList.minNumber..numbersList.maxNumber step numbersList.stepSize) {
            context("_bracketNumber=$bracketNumber") {
                val parameter = AutoBracketProgram.Parameter(
                        iso = Iso.AUTO,
                        //shutterSpeed = ShutterSpeed.AUTO,
                        colorTemperature = 5500,
                        exposureProgram = ExposureProgram.AUTO,
                        exposureCompensation = ExposureCompensation(0.0),
                        whiteBalance = WhiteBalance.AUTO,
                )
                val parameters = List(bracketNumber) { parameter }
                val program = AutoBracketProgram(parameters)
                theta.setOption(AUTO_BRACKET, program)
            }
        }
    }

    context("_bitrate") {
        CaptureMode.values().forEach { captureMode ->
            context("captureMode=$captureMode") {
                theta.setOption(CAPTURE_MODE, captureMode)
                var support by Delegates.notNull<List<Bitrate>>()
                test("Get support") {
                    support = theta.getOption(BITRATE_SUPPORT)
                }
                support.forEach { value ->
                    context(value.toString()) {
                        test("Set") {
                            theta.setOption(BITRATE, value)
                        }
                        test("Get") {
                            theta.getOption(BITRATE) shouldBe value
                        }
                    }
                }
            }
        }
    }

    xcontext("_bluetoothClassicEnable") {
        // It is not supported by THETA X.
    }

    context("_bluetoothPower") {
        var support by Delegates.notNull<List<BluetoothPower>>()
        test("Get support") {
            support = theta.getOption(BLUETOOTH_POWER_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(BLUETOOTH_POWER, value)
                }
                test("Get") {
                    theta.getOption(BLUETOOTH_POWER) shouldBe value
                }
            }
        }
    }

    xcontext("_burstMode") {
        // It is not supported by THETA X.
    }

    xcontext("_burstOption") {
        // It is not supported by THETA X.
    }

    context("captureInterval") {
        theta.setOption(CAPTURE_MODE, CaptureMode.IMAGE)
        val formats = theta.getOption(FILE_FORMAT_SUPPORT).filter { it.type == FileFormat.Type.JPEG }
        formats.forEach { format ->
            context("fileFormat=$format") {
                theta.setOption(FILE_FORMAT, format)
                val supports = theta.getOption(CAPTURE_INTERVAL_SUPPORT)
                listOf(supports.minInterval, supports.maxInterval).forEach { value ->
                    context(value.toString()) {
                        test("Set") {
                            theta.setOption(CAPTURE_INTERVAL, value)
                        }
                        test("Get") {
                            theta.getOption(CAPTURE_INTERVAL) shouldBe value
                        }
                    }
                }
            }
        }
    }

    context("captureMode") {
        var support by Delegates.notNull<List<CaptureMode>>()
        test("Get support") {
            support = theta.getOption(CAPTURE_MODE_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(CAPTURE_MODE, value)
                }
                test("Get") {
                    theta.getOption(CAPTURE_MODE) shouldBe value
                }
            }
        }
    }

    context("_cameraControlSource") {
        var support by Delegates.notNull<List<CameraControlSource>>()
        test("Get support") {
            support = theta.getOption(CAMERA_CONTROL_SOURCE_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(CAMERA_CONTROL_SOURCE, value)
                }
                test("Get") {
                    theta.getOption(CAMERA_CONTROL_SOURCE) shouldBe value
                }
            }
        }
    }

    context("_cameraMode") {
        test("Get support") {
            theta.getOption(CAMERA_MODE_SUPPORT).toSet() shouldBe CameraMode.values()
        }
        context("capture") {
            val value = CameraMode.CAPTURE
            test("Set") {
                theta.setOption(CAMERA_MODE, value)
            }
            test("Get") {
                theta.getOption(CAMERA_MODE) shouldBe value
            }
        }

        context("playback") {
            xtest("Get") {
                theta.getOption(CAMERA_MODE) shouldBe CameraMode.PLAYBACK
            }
        }

        context("setting") {
            xtest("Get") {
                theta.getOption(CAMERA_MODE) shouldBe CameraMode.SETTING
            }
        }

        context("plugin") {
            xtest("Get") {
                theta.getOption(CAMERA_MODE) shouldBe CameraMode.PLUGIN
            }
        }
    }

    context("captureNumber") {
        var support by Delegates.notNull<CaptureNumberSupport>()
        test("Get support") {
            support = theta.getOption(CAPTURE_NUMBER_SUPPORT)
        }
        listOf(support.limitless, support.minNumber, support.maxNumber).forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(CAPTURE_NUMBER, value)
                }
                test("Get") {
                    theta.getOption(CAPTURE_NUMBER) shouldBe value
                }
            }
        }
    }

    context("clientVersion") {
        // THETA X does not support API v2.0
        test("Set") {
            theta.setOption(CLIENT_VERSION, ApiVersion.V2_1)
        }
        test("Get") {
            theta.getOption(CLIENT_VERSION) shouldBe ApiVersion.V2_1
        }
    }

    context("_colorTemperature") {
        var support by Delegates.notNull<ColorTemperatureSupport>()
        test("Get support") {
            support = theta.getOption(COLOR_TEMPERATURE_SUPPORT)
        }
        listOf(support.minTemperature, support.maxTemperature).forEach { temperature ->
            context(temperature.toString()) {
                test("Set") {
                    theta.setOption(COLOR_TEMPERATURE, temperature)
                }
                test("Get") {
                    theta.getOption(COLOR_TEMPERATURE) shouldBe temperature
                }
            }
        }
    }

    xcontext("_compositeShootingOutputInterval") {
        // It is not supported by THETA X.
    }

    xcontext("_compositeShootingTime") {
        // It is not supported by THETA X.
    }

    context("continuousNumber") {
        theta.setOption(CAPTURE_MODE, CaptureMode.IMAGE)
        val support = theta.getOption(FILE_FORMAT_SUPPORT)
        support.forEach { format ->
            context("FileFormat=$format") {
                theta.setOption(FILE_FORMAT, format)
                test("Get support") {
                    theta.getOption(CONTINUOUS_NUMBER_SUPPORT)
                }
                test("Get") {
                    theta.getOption(CONTINUOUS_NUMBER)
                }
            }
        }
    }

    context("dateTimeZone") {
        test("Set") {
            theta.setOption(DATE_TIME_ZONE, OffsetDateTime.now())
        }
        test("Get") {
            theta.getOption(DATE_TIME_ZONE)
        }
    }

    context("exposureCompensation") {
        listOf(CaptureMode.IMAGE, CaptureMode.VIDEO).forEach { captureMode ->
            context("captureMode=$captureMode") {
                theta.setOption(CAPTURE_MODE, captureMode)
                var support by Delegates.notNull<List<ExposureCompensation>>()
                test("Get support") {
                    support = theta.getOption(EXPOSURE_COMPENSATION_SUPPORT)
                }
                support.forEach { value ->
                    test("Set") {
                        theta.setOption(EXPOSURE_COMPENSATION, value)
                    }
                    test("Get") {
                        theta.getOption(EXPOSURE_COMPENSATION) shouldBe value
                    }
                }
            }
        }
    }

    context("exposureDelay") {
        listOf(CaptureMode.IMAGE, CaptureMode.VIDEO).forEach { captureMode ->
            context("captureMode=$captureMode") {
                theta.setOption(CAPTURE_MODE, captureMode)
                var support by Delegates.notNull<List<Int>>()
                test("Get support") {
                    support = theta.getOption(EXPOSURE_DELAY_SUPPORT)
                }
                support.forEach { value ->
                    context(value.toString()) {
                        test("Set") {
                            theta.setOption(EXPOSURE_DELAY, value)
                        }
                        test("Get") {
                            theta.getOption(EXPOSURE_DELAY) shouldBe value
                        }
                    }
                }
            }
        }
    }

    context("exposureProgram") {
        listOf(CaptureMode.IMAGE, CaptureMode.VIDEO).forEach { captureMode ->
            context("captureMode=$captureMode") {
                theta.setOption(CAPTURE_MODE, captureMode)
                var support by Delegates.notNull<List<ExposureProgram>>()
                test("Get support") {
                    support = theta.getOption(EXPOSURE_PROGRAM_SUPPORT)
                }
                support.forEach { value ->
                    test("Set") {
                        theta.setOption(EXPOSURE_PROGRAM, value)
                    }
                    test("Get") {
                        theta.getOption(EXPOSURE_PROGRAM) shouldBe value
                    }
                }
            }
        }
    }

    context("_faceDetect") {
        var support by Delegates.notNull<List<FaceDetect>>()
        test("Get support") {
            support = theta.getOption(FACE_DETECT_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(FACE_DETECT, value)
                }
                test("Get") {
                    theta.getOption(FACE_DETECT) shouldBe value
                }
            }
        }
    }

    context("fileFormat") {
        listOf(CaptureMode.IMAGE, CaptureMode.VIDEO).forEach { captureMode ->
            context("captureMode=$captureMode") {
                theta.setOption(CAPTURE_MODE, captureMode)
                var support by Delegates.notNull<List<FileFormat>>()
                test("Get support") {
                    support = theta.getOption(FILE_FORMAT_SUPPORT)
                }
                support.forEach { value ->
                    context("Set") {
                        theta.setOption(FILE_FORMAT, value)
                    }
                    context("Get") {
                        theta.getOption(FILE_FORMAT) shouldBe value
                    }
                }
            }
        }
    }

    context("_filter") {
        theta.setOptions {
            put(CAPTURE_MODE, CaptureMode.IMAGE)
            put(FILE_FORMAT, FileFormat.JPEG_5504_2752)
        }
        var support by Delegates.notNull<List<Filter>>()
        test("Get support") {
            support = theta.getOption(FILTER_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(FILTER, value)
                }
                test("Get") {
                    theta.getOption(FILTER) shouldBe value
                }
            }
        }
    }

    context("_function") {
        var support by Delegates.notNull<List<Function>>()
        test("Get support") {
            support = theta.getOption(FUNCTION_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(FUNCTION, value)
                }
                test("Get") {
                    theta.getOption(FUNCTION) shouldBe value
                }
            }
        }
    }

    context("_gain") {
        var support by Delegates.notNull<List<MicrophoneGain>>()
        test("Get support") {
            support = theta.getOption(MICROPHONE_GAIN_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(MICROPHONE_GAIN, value)
                }
                test("Get") {
                    theta.getOption(MICROPHONE_GAIN) shouldBe value
                }
            }
        }
    }

    context("gpsInfo") {
        val value = GpsInfo(0.0, 0.0, 0.0, OffsetDateTime.now(), "WGS84")
        test("Set") {
            theta.setOption(GPS_INFORMATION, value)
        }
        test("Get") {
            theta.getOption(GPS_INFORMATION) shouldBe value
        }
    }

    context("_gpsTagRecording") {
        var support by Delegates.notNull<List<GPSTagRecording>>()
        test("Get support") {
            support = theta.getOption(GPS_TAG_RECORDING_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(GPS_TAG_RECORDING, value)
                }
                test("Get") {
                    theta.getOption(GPS_TAG_RECORDING) shouldBe value
                }
            }
        }
    }

    xcontext("_HDMIreso") {
        // It is not supported by THETA X.
    }

    context("_imageStitching") {
        var support by Delegates.notNull<List<ImageStitching>>()
        test("Get support") {
            support = theta.getOption(IMAGE_STITCHING_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(IMAGE_STITCHING, value)
                }
                test("Get") {
                    theta.getOption(IMAGE_STITCHING) shouldBe value
                }
            }
        }
    }

    context("iso") {
        listOf(CaptureMode.IMAGE, CaptureMode.VIDEO).forEach { captureMode ->
            context("captureMode=$captureMode") {
                theta.setOptions {
                    put(CAPTURE_MODE, captureMode)
                    put(EXPOSURE_PROGRAM, ExposureProgram.ISO)
                }
                var support by Delegates.notNull<List<Iso>>()
                test("Get support") {
                    support = theta.getOption(ISO_SUPPORT)
                }
                support.forEach { value ->
                    test("Set") {
                        theta.setOption(ISO, value)
                    }
                    test("Get") {
                        theta.getOption(ISO) shouldBe value
                    }
                }
            }
        }
    }

    context("isoAutoHighLimit") {
        var support by Delegates.notNull<List<Iso>>()
        test("Get support") {
            support = theta.getOption(ISO_AUTO_HIGH_LIMIT_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(ISO_AUTO_HIGH_LIMIT, value)
                }
                test("Get") {
                    theta.getOption(ISO_AUTO_HIGH_LIMIT) shouldBe value
                }
            }
        }
    }

    context("_language") {
        var support by Delegates.notNull<List<String>>()
        test("Get support") {
            support = theta.getOption(LANGUAGE_SUPPORT)
        }
        support.forEach { value ->
            context(value) {
                test("Set") {
                    theta.setOption(LANGUAGE, value)
                }
                test("Get") {
                    theta.getOption(LANGUAGE) shouldBe value
                }
            }
        }
    }

    context("_latestEnabledExposureDelayTime") {
        test("Get") {
            theta.getOption(LATEST_ENABLED_EXPOSURE_DELAY_TIME) shouldNotBe 0
        }
    }

    context("_maxRecordableTime") {
        var support by Delegates.notNull<List<Int>>()
        test("Get support") {
            support = theta.getOption(MAX_RECORDABLE_TIME_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(MAX_RECORDABLE_TIME, value)
                }
                test("Get") {
                    theta.getOption(MAX_RECORDABLE_TIME) shouldBe value
                }
            }
        }
    }

    xcontext("_microphone") {
        // It is not supported by THETA X.
    }

    xcontext("_microphoneChannel") {
        // It is not supported by THETA X.
    }

    context("_networkType") {
        xcontext(NetworkType.OFF.toString()) {
            test("Set") {
                theta.setOption(NETWORK_TYPE, NetworkType.OFF)
            }
            test("Get") {
                theta.getOption(NETWORK_TYPE) shouldBe NetworkType.OFF
            }
        }
        listOf(NetworkType.ACCESS_POINT, NetworkType.CLIENT).forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(NETWORK_TYPE, value)
                }
                test("Get") {
                    theta.getOption(NETWORK_TYPE) shouldBe value
                }
            }
        }
    }

    context("offDelay") {
        var support by Delegates.notNull<List<Int>>()
        test("Get support") {
            support = theta.getOption(OFF_DELAY_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(OFF_DELAY, value)
                }
                test("Get") {
                    theta.getOption(OFF_DELAY) shouldBe value
                }
            }
        }
    }

    context("_powerSaving") {
        var support by Delegates.notNull<List<PowerSaving>>()
        test("Get support") {
            support = theta.getOption(POWER_SAVING_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(POWER_SAVING, value)
                }
                test("Get") {
                    theta.getOption(POWER_SAVING) shouldBe value
                }
            }
        }
    }

    context("_password") {
        theta.setOption(NETWORK_TYPE, NetworkType.ACCESS_POINT)
        test("Set") {
            theta.setOption(PASSWORD, "hello")
        }
    }

    context("previewFormat") {
        var support by Delegates.notNull<List<PreviewFormat>>()
        test("Get support") {
            support = theta.getOption(PREVIEW_FORMAT_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(PREVIEW_FORMAT, value)
                }
                test("Get") {
                    theta.getOption(PREVIEW_FORMAT) shouldBe value
                }
            }
        }
    }

    context("remainingPictures") {
        test("Get") {
            theta.getOption(REMAINING_PICTURES)
        }
    }

    context("remainingSpace") {
        test("Get") {
            theta.getOption(REMAINING_SPACE)
        }
    }

    context("remainingVideoSeconds") {
        test("Get") {
            theta.getOption(REMAINING_VIDEO_SECONDS)
        }
    }

    context("_shootingMethod") {
        theta.setOption(CAPTURE_MODE, CaptureMode.IMAGE)
        var support by Delegates.notNull<List<ShootingMethod>>()
        test("Get support") {
            support = theta.getOption(SHOOTING_METHOD_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(SHOOTING_METHOD, value)
                }
                test("Set") {
                    theta.getOption(SHOOTING_METHOD) shouldBe value
                }
            }
        }
    }

    context("shutterSpeed") {
        theta.setOption(EXPOSURE_PROGRAM, ExposureProgram.SHUTTER_SPEED)
        var support by Delegates.notNull<List<ShutterSpeed>>()
        test("Get support") {
            support = theta.getOption(SHUTTER_SPEED_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(SHUTTER_SPEED, value)
                }
                test("Get") {
                    theta.getOption(SHUTTER_SPEED) shouldBe value
                }
            }
        }
    }

    context("_shutterVolume") {
        var support by Delegates.notNull<ShutterVolumeSupport>()
        test("Get support") {
            support = theta.getOption(SHUTTER_VOLUME_SUPPORT)
        }
        listOf(support.minShutterVolume, support.maxShutterVolume).forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(SHUTTER_VOLUME, value)
                }
                test("Get") {
                    theta.getOption(SHUTTER_VOLUME) shouldBe value
                }
            }
        }
    }

    context("sleepDelay") {
        var support by Delegates.notNull<List<Int>>()
        test("Get support") {
            support = theta.getOption(SLEEP_DELAY_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(SLEEP_DELAY, value)
                }
                test("Get") {
                    theta.getOption(SLEEP_DELAY) shouldBe value
                }
            }
        }
    }

    context("_timeShift") {
        var support by Delegates.notNull<TimeShiftSupport>()
        test("Get support") {
            support = theta.getOption(TIME_SHIFT_SUPPORT)
        }
        val value = TimeShift(
                support.firstShooting.first(),
                support.firstInterval.minInterval,
                support.secondInterval.minInterval,
        )
        test("Set") {
            theta.setOption(TIME_SHIFT, value)
        }
        test("Get") {
            theta.getOption(TIME_SHIFT) shouldBe value
        }
    }

    context("_topBottomCorrection") {
        var support by Delegates.notNull<List<TopBottomCorrection>>()
        test("Get support") {
            support = theta.getOption(TOP_BOTTOM_CORRECTION_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(TOP_BOTTOM_CORRECTION, value)
                }
                test("Get") {
                    theta.getOption(TOP_BOTTOM_CORRECTION) shouldBe value
                }
            }
        }
    }

    context("_topBottomCorrectionRotation") {
        var support by Delegates.notNull<TopBottomCorrectionRotationSupport>()
        test("Get support") {
            support = theta.getOption(TOP_BOTTOM_CORRECTION_ROTATION_SUPPORT)
        }
        val value = TopBottomCorrectionRotation(
                support.pitch.minPitch,
                support.roll.minRoll,
                support.yaw.minYaw,
        )
        test("Set") {
            theta.setOption(TOP_BOTTOM_CORRECTION_ROTATION, value)
        }
        test("Get") {
            theta.getOption(TOP_BOTTOM_CORRECTION_ROTATION) shouldBe value
        }
    }

    context("totalSpace") {
        test("Get") {
            theta.getOption(TOTAL_SPACE) shouldBeGreaterThan 0
        }
    }

    context("_username") {
        test("Set") {
            theta.setOption(USERNAME, "dummy")
        }
    }

    context("videoStitching") {
        var support by Delegates.notNull<List<VideoStitching>>()
        test("Get support") {
            support = theta.getOption(VIDEO_STITCHING_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(VIDEO_STITCHING, value)
                }
                test("Get") {
                    theta.getOption(VIDEO_STITCHING) shouldBe value
                }
            }
        }
    }

    xcontext("_visibilityReduction") {
        // It is not supported by THETA X.
    }

    context("whiteBalance") {
        var support by Delegates.notNull<List<WhiteBalance>>()
        test("Get support") {
            support = theta.getOption(WHITE_BALANCE_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(WHITE_BALANCE, value)
                }
                test("Get") {
                    theta.getOption(WHITE_BALANCE) shouldBe value
                }
            }
        }
    }

    xcontext("_wlanChannel") {
        // It is not supported by THETA X.
    }

    context("_wlanFrequency") {
        var support by Delegates.notNull<List<WlanFrequency>>()
        test("Get support") {
            support = theta.getOption(WLAN_FREQUENCY_SUPPORT)
        }
        support.forEach { value ->
            context(value.toString()) {
                test("Set") {
                    theta.setOption(WLAN_FREQUENCY, value)
                }
                test("Get") {
                    theta.getOption(WLAN_FREQUENCY) shouldBe value
                }
            }
        }
    }
})
