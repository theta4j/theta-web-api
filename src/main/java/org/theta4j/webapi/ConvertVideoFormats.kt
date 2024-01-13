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

import kotlinx.serialization.Serializable
import org.theta4j.osc.URISerializer
import java.net.URI

object ConvertVideoFormats {
    @Serializable
    data class Parameter(
            @Serializable(URISerializer::class)
            val fileUrl: URI,

            val size: Size,

            val projectionType: ProjectionType? = null,

            val codec: Codec? = null,

            val topBottomCorrectionType: TopBottomCorrectionType? = null,
    )

    @Serializable
    data class Result internal constructor(
            @Serializable(URISerializer::class)
            val fileUrl: URI,
    )

    @Serializable
    @JvmInline
    value class Size private constructor(private val value: String) {
        companion object {
            val W3840_H1920 = Size("3840x1920")

            val W1920_H960 = Size("1920x960")
        }

        val width: Int get() = value.split("x").getOrNull(0)?.toIntOrNull() ?: -1

        val height: Int get() = value.split("x").getOrNull(1)?.toIntOrNull() ?: -1
    }

    @Serializable
    @JvmInline
    value class ProjectionType private constructor(private val value: String) {
        companion object {
            val EQUIRECTANGULAR = ProjectionType("Equirectangular")
        }
    }

    @Serializable
    @JvmInline
    value class Codec private constructor(private val value: String) {
        companion object {
            val H264_MPEG4_AVC = Codec("H.264/MPEG-4 AVC")
        }
    }

    @Serializable
    @JvmInline
    value class TopBottomCorrectionType private constructor(private val value: String) {
        companion object {
            val APPLY = TopBottomCorrectionType("Apply")

            val APPLY_FIXED_DIRECTION = TopBottomCorrectionType("ApplyFixedDirection")

            val DISAPPLY = TopBottomCorrectionType("Disapply")
        }
    }
}
