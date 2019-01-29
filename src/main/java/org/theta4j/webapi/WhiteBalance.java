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
 * White balance.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/white_balance.html">whiteBalance · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum WhiteBalance {
    /**
     * Automatic
     */
    @SerializedName("auto") AUTO,

    /**
     * Outdoor
     */
    @SerializedName("daylight") DAYLIGHT,

    /**
     * Shade
     */
    @SerializedName("shade") SHADE,

    /**
     * Cloudy
     */
    @SerializedName("cloudy-daylight") CLOUDY_DAYLIGHT,

    /**
     * Incandescent light 1
     */
    @SerializedName("incandescent") INCANDESCENT,

    /**
     * Incandescent light 2
     */
    @SerializedName("_warmWhiteFluorescent") WARM_WHITE_FLUORESCENT,

    /**
     * Fluorescent light 1 (daylight)
     */
    @SerializedName("_dayLightFluorescent") DAYLIGHT_FLUORESCENT,

    /**
     * Fluorescent light 2 (natural white)
     */
    @SerializedName("_dayWhiteFluorescent") DAY_WHITE_FLUORESCENT,

    /**
     * Fluorescent light 3 (white)
     */
    @SerializedName("fluorescent") FLUORESCENT,

    /**
     * Fluorescent light 4 (light bulb color)
     */
    @SerializedName("_bulbFluorescent") BULB_FLUORESCENT,

    /**
     * Custom color temperature.
     *
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_color_temperature.html">_colorTemperature · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @SerializedName("_colorTemperature") COLOR_TEMPERATURE
}
