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
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import java.math.RoundingMode.HALF_UP

@Serializable
@JvmInline
value class ShutterSpeed(val value: Double) {
    companion object {
        val AUTO = ShutterSpeed(0.0)
    }

    override fun toString(): String {
        if (value == 0.0) return "Auto"
        if (value >= 1.0) return BigDecimal(value).round().toPlainString()
        val denominator = BigDecimal(1 / value).round().toPlainString()
        return "1/$denominator"
    }
}

private fun BigDecimal.round(): BigDecimal = if (toDouble() > 200.0) {
    // Rounds off to the tens place.
    setScale(-1, HALF_UP).setScale(0)
} else {
    // Rounds off to the first decimal place.
    setScale(1, HALF_UP).removeDecimalPartIfNeed()
}

internal fun BigDecimal.removeDecimalPartIfNeed(): BigDecimal {
    val zero = ZERO.setScale(scale())
    return if (rem(ONE) == zero) setScale(0) else this
}
