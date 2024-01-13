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

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ApertureTest : FunSpec({
    context("toString") {
        listOf(
            0.0 to "Auto",
            2.0 to "F2.0",
            2.1 to "F2.1",
            3.5 to "F3.5",
            5.6 to "F5.6",
        ).forEach { (value, string) ->
            test("Aperture($value).toString() should be $string") {
                Aperture(value).toString() shouldBe string
            }
        }
    }
})
