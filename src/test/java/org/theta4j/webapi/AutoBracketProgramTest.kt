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
import kotlinx.serialization.json.*

class AutoBracketProgramTest : FunSpec({
    context("Encode and decode AutoBracketProgram") {
        val parameter = AutoBracketProgram.Parameter(
            iso = Iso.AUTO,
            shutterSpeed = ShutterSpeed.AUTO,
            colorTemperature = 5500,
        )
        val program = AutoBracketProgram(parameter)
        val json = buildJsonObject {
            put("_bracketNumber", 1)
            putJsonArray("_bracketParameters") {
                addJsonObject {
                    put("iso", Iso.AUTO.value)
                    put("shutterSpeed", ShutterSpeed.AUTO.value)
                    put("colorTemperature", 5500)
                }
            }
        }

        test("Encode") {
            val encoded = Json.encodeToJsonElement(program)
            encoded shouldBe json
        }

        test("Decode") {
            val decoded = Json.decodeFromJsonElement<AutoBracketProgram>(json)
            decoded shouldBe program
        }
    }
})
