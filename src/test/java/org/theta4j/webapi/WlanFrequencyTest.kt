/*
 * Copyright (C) 2023 theta4j project
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

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.theta4j.webapi.WlanFrequency.Companion.F2_4
import org.theta4j.webapi.WlanFrequency.Companion.F5

class WlanFrequencyTest : FunSpec({
    context("toString()") {
        listOf(
                F2_4 to "2.4GHz",
                F5 to "5GHz",
        ).forEach { (value, string) ->
            test(string) {
                value.toString() shouldBe string
            }
        }
    }
})
