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

import com.google.gson.annotations.SerializedName;

/**
 * Shooting mode supported by THETA.
 * Actual supported values are different between each models.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/capture_mode.html">captureMode · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum CaptureMode {
    /**
     * Still image capture mode.
     */
    @SerializedName("image") IMAGE,

    /**
     * Video capture mode.
     */
    @SerializedName("video") VIDEO,

    /**
     * Interval capture mode.
     */
    @SerializedName("interval") INTERVAL,

    /**
     * Live streaming mode.
     */
    @SerializedName("_liveStreaming") LIVE_STREAMING
}
