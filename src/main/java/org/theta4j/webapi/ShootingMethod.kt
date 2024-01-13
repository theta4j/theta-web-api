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
@JvmInline
value class ShootingMethod private constructor(private val value: String) {
    companion object {
        val NORMAL = ShootingMethod("normal")

        val INTERVAL = ShootingMethod("interval")

        val MOVE_INTERVAL = ShootingMethod("moveInterval")

        val FIXED_INTERVAL = ShootingMethod("fixedInterval")

        val BRACKET = ShootingMethod("bracket")

        val COMPOSITE = ShootingMethod("composite")

        val CONTINUOUS = ShootingMethod("continuous")

        val TIME_SHIFT = ShootingMethod("timeShift")
    }
}
