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
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Serializable
data class FileInfo(
        val name: String? = null,

        @Serializable(URISerializer::class)
        val fileUrl: URI,

        val size: Int = 0,

        @Serializable(DateTimeZoneSerializer::class)
        val dateTimeZone: OffsetDateTime? = null,

        @Serializable(DateTimeSerializer::class)
        val dateTime: LocalDateTime? = null,

        val lat: Int = 0,

        val lng: Int = 0,

        val width: Int = 0,

        val height: Int = 0,

        val thumbnail: String? = null,

        @SerialName("_thumbSize")
        val thumbSize: Int = 0,

        @SerialName("_intervalCaptureGroupId")
        val intervalCaptureGroupId: String? = null,

        @SerialName("_compositeShootingGroupId")
        val compositeShootingGroupId: String? = null,

        @SerialName("_autoBracketGroupId")
        val autoBracketGroupId: String? = null,

        @SerialName("_recordTime")
        val recordTime: Int = 0,

        val isProcessed: Boolean = false,

        @Serializable(EmptyURISerializer::class)
        val previewUrl: URI? = null,

        @SerialName("_codec")
        val codec: String? = null,

        @SerialName("_projectionType")
        val projectionType: ProjectionType? = null,
) {
    @Serializable
    @JvmInline
    value class ProjectionType private constructor(private val value: String) {
        companion object {
            val EQUIRECTANGULAR = ProjectionType("Equirectangular")

            val DUAL_FISHEYE = ProjectionType("Dual-Fisheye")
        }
    }
}
