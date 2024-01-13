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

@Serializable
data class PreviewFormat(
        val width: Int,
        val height: Int,
        val framerate: Int,
) {
    companion object {
        val FORMAT_1920_960_8P = PreviewFormat(1920, 960, 8)
        val FORMAT_1024_512_30P = PreviewFormat(1024, 512, 30)
        val FORMAT_1024_512_8P = PreviewFormat(1024, 512, 8)
        val FORMAT_640_320_30P = PreviewFormat(640, 320, 30)
        val FORMAT_640_320_10P = PreviewFormat(640, 320, 10)
        val FORMAT_640_320_8P = PreviewFormat(640, 320, 8)
    }
}
