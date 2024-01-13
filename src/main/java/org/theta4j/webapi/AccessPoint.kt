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
data class AccessPoint(
        val ssid: String,

        val security: Security = Security.NONE,

        val isStealth: Boolean? = null,

        val password: String? = null,

        val connectionPriority: Int? = null,

        val ipAddressAllocation: IpAddressAllocation? = null,

        val ipAddress: String? = null,

        val subnetMask: String? = null,

        val defaultGateway: String? = null,
) {
    @Serializable
    @JvmInline
    value class Security private constructor(private val value: String) {
        companion object {
            val NONE = Security("none")

            val WEP = Security("WEP")

            val WPA_WPA2_PSK = Security("WPA/WPA2 PSK")
        }
    }

    @Serializable
    @JvmInline
    value class IpAddressAllocation private constructor(private val value: String) {
        companion object {
            val DYNAMIC = IpAddressAllocation("dynamic")

            val STATIC = IpAddressAllocation("static")
        }
    }
}
