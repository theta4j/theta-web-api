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
 * Wireless LAN frequency of the camera.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_wlan_frequency.html">_wlanFrequency · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum WlanFrequency {
    /**
     * 2.4GHz
     */
    @SerializedName("2.4") F2_4,

    /**
     * 5GHz
     */
    @SerializedName("5") F5
}
