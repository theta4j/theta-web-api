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

import kotlinx.serialization.*
import java.time.LocalDateTime

@Serializable
data class Exif(
        @SerialName("ExifVersion")
        val exifVersion: String? = null,

        @SerialName("ImageDescription")
        val imageDescription: String? = null,

        @SerialName("DateTime")
        @Serializable(DateTimeSerializer::class)
        val dateTime: LocalDateTime? = null,

        @SerialName("ImageWidth")
        @Serializable(IntAsDoubleSerializer::class)
        val imageWidth: Int = 0,

        @SerialName("ImageLength")
        @Serializable(IntAsDoubleSerializer::class)
        val imageLength: Int = 0,

        @SerialName("ColorSpace")
        @Serializable(IntAsDoubleSerializer::class)
        val colorSpace: Int = 0,

        @SerialName("Compression")
        @Serializable(IntAsDoubleSerializer::class)
        val compression: Int = 0,

        @SerialName("Orientation")
        @Serializable(IntAsDoubleSerializer::class)
        val orientation: Int = 0,

        @SerialName("Flash")
        @Serializable(IntAsDoubleSerializer::class)
        val flash: Int = 0,

        @SerialName("FocalLength")
        val focalLength: Double? = null,

        @SerialName("WhiteBalance")
        @Serializable(IntAsDoubleSerializer::class)
        val whiteBalance: Int = 0,

        @SerialName("ExposureTime")
        val exposureTime: Double? = null,

        @SerialName("FNumber")
        val fNumber: Double? = null,

        @SerialName("ExposureProgram")
        @Serializable(IntAsDoubleSerializer::class)
        val exposureProgram: Int = 0,

        @SerialName("PhotographicSensitivity")
        @Serializable(IntAsDoubleSerializer::class)
        val photographicSensitivity: Int = 0,

        @SerialName("ApertureValue")
        val apertureValue: Double? = null,

        @SerialName("BrightnessValue")
        val brightnessValue: Double? = null,

        @SerialName("ExposureBiasValue")
        val exposureBiasValue: Double? = null,

        @SerialName("GPSLatitudeRef")
        val gpsLatitudeRef: String? = null,

        @SerialName("GPSLatitude")
        val gpsLatitude: Double? = null,

        @SerialName("GPSLongitudeRef")
        val gpsLongitudeRef: String? = null,

        @SerialName("GPSLongitude")
        val gpsLongitude: Double? = null,

        @SerialName("Make")
        val make: String? = null,

        @SerialName("Model")
        val model: String? = null,

        @SerialName("Software")
        val software: String? = null,

        @SerialName("Copyright")
        val copyright: String? = null,
)
