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
import java.math.BigDecimal
import java.math.RoundingMode

@Serializable
@JvmInline
value class WlanFrequency private constructor(private val value: Double) {
    companion object {
        val F2_4 = WlanFrequency(2.4)

        val F5 = WlanFrequency(5.0)
    }

    override fun toString(): String {
        val string = BigDecimal(value).setScale(1, RoundingMode.HALF_UP).removeDecimalPartIfNeed()
        return "${string}GHz"
    }
}
