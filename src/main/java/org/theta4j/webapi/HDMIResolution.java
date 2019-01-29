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
 * Resolution output via HDMI.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_hdmi_reso.html">_HDMIreso · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum HDMIResolution {
    /**
     * Automatic
     */
    @SerializedName("Auto") AUTO,

    /**
     * 1920x1080
     */
    @SerializedName("L") RES_1920_1080,

    /**
     * 1280x720
     */
    @SerializedName("M") RES_1280_720,

    /**
     * 720x480
     */
    @SerializedName("S") RES_720_480
}
