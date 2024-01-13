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

@Serializable
data class FileFormat(
        val type: Type,

        val width: Int,

        val height: Int,

        @SerialName("_framerate")
        val framerate: Int? = null,

        @SerialName("_codec")
        val codec: String? = null,
) {
    companion object {
        private const val H264_MPEG4_AVC = "H.264/MPEG-4 AVC"

        val JPEG_11008_5504 = FileFormat(Type.JPEG, 11008, 5504)
        val JPEG_6720_3306 = FileFormat(Type.JPEG, 6720, 3306)
        val JPEG_5504_2752 = FileFormat(Type.JPEG, 5504, 2752)
        val JPEG_5376_2688 = FileFormat(Type.JPEG, 5376, 2688)
        val JPEG_2048_1024 = FileFormat(Type.JPEG, 2048, 1024)

        val RAW_6720_3360 = FileFormat(Type.RAW, 6720, 3360)

        val MP4_7680_3840_10P_H264AVC = FileFormat(Type.MP4, 7680, 3840, 10, H264_MPEG4_AVC)
        val MP4_5760_2752_30P_H264AVC = FileFormat(Type.MP4, 5760, 2752, 30, H264_MPEG4_AVC)
        val MP4_3840_1920_60P_H264AVC = FileFormat(Type.MP4, 3840, 1920, 60, H264_MPEG4_AVC)
        val MP4_3840_1920_30P_H264AVC = FileFormat(Type.MP4, 3840, 1920, 30, H264_MPEG4_AVC)
        val MP4_1920_960_60P_H264AVC = FileFormat(Type.MP4, 1920, 960, 60, H264_MPEG4_AVC)
        val MP4_1920_960_30P_H264AVC = FileFormat(Type.MP4, 1920, 960, 30, H264_MPEG4_AVC)
        val MP4_3840_1920_H264AVC = FileFormat(Type.MP4, 3840, 1920, null, H264_MPEG4_AVC)
        val MP4_1920_960_H264AVC = FileFormat(Type.MP4, 1920, 960, null, H264_MPEG4_AVC)
        val MP4_1920_1080 = FileFormat(Type.MP4, 1920, 1080)
        val MP4_1280_720 = FileFormat(Type.MP4, 1280, 720)
    }

    @Serializable
    @JvmInline
    value class Type private constructor(private val value: String) {
        companion object {
            val JPEG = Type("jpeg")

            val RAW = Type("raw+")

            val MP4 = Type("mp4")
        }
    }
}
