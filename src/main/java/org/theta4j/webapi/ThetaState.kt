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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.theta4j.osc.EmptyURISerializer
import org.theta4j.osc.URISerializer
import java.net.URI

@Serializable
data class ThetaState internal constructor(
        val batteryLevel: Double,

        @Serializable(URISerializer::class)
        val storageUri: URI,

        @SerialName("_storageID")
        val storageID: String?,

        @SerialName("_captureStatus")
        val captureState: CaptureState,

        @SerialName("_recordedTime")
        val recordedTime: Int,

        @SerialName("_recordableTime")
        val recordableTime: Int,

        @SerialName("_capturedPictures")
        val capturedPictures: Int,

        @SerialName("_compositeShootingElapsedTime")
        val compositeShootingElapsedTime: Int?,

        @Serializable(EmptyURISerializer::class)
        @SerialName("_latestFileUrl")
        val latestFileUrl: URI?,

        @SerialName("_batteryState")
        val batteryState: BatteryState,

        @SerialName("_apiVersion")
        val apiVersion: ApiVersion,

        @SerialName("_pluginRunning")
        val isPluginRunning: Boolean,

        @SerialName("_pluginWebServer")
        val pluginWebServer: Boolean,

        @SerialName("_function")
        val function: Function?,

        @SerialName("_mySettingChanged")
        val isMySettingChanged: Boolean?,

        @SerialName("_currentMicrophone")
        val currentMicrophone: Microphone?,

        @SerialName("_currentStorage")
        val currentStorage: StorageType?,

        @SerialName("_cameraError")
        val cameraError: List<CameraError>,

        @SerialName("_batteryInsert")
        val batteryInsert: Boolean?,
) {
    @Serializable
    @JvmInline
    value class CaptureState private constructor(private val value: String) {
        companion object {
            val IDLE = CaptureState("idle")

            val SHOOTING = CaptureState("shooting")

            val SELF_TIMER_COUNTDOWN = CaptureState("self-timer countdown")

            val BRACKET_SHOOTING = CaptureState("bracket shooting")

            val CONVERTING = CaptureState("converting")

            val TIME_SHIFT_SHOOTING = CaptureState("timeShift shooting")

            val CONTINUOUS_SHOOTING = CaptureState("continuous shooting")

            val RETROSPECTIVE_IMAGE_RECORDING = CaptureState("retrospective image recording")
        }
    }

    @Serializable
    @JvmInline
    value class BatteryState private constructor(private val value: String) {
        companion object {
            val DISCONNECT = BatteryState("disconnect")

            val CHARGING = BatteryState("charging")

            val CHARGED = BatteryState("charged")
        }
    }

    @Serializable
    @JvmInline
    value class StorageType private constructor(private val value: String) {
        companion object {
            val INTERNAL_MEMORY = StorageType("IN")

            val SD_CARD = StorageType("SD")
        }
    }
}
