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

package org.theta4j.osc

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*

class OptionSetTest : FunSpec({
    val opt1 = Option.create("opt_int", Int.serializer())
    val opt2 = Option.create("opt_string", String.serializer())
    val opt3 = ArrayOption.create("opt_int_array", Int.serializer())

    context("Decode bad JSON throws Exception") {
        listOf(
            """{""",
            """{}""",
            """{"hello":null}""",
            """{"options":null}""",
            """{"options":1}""",
            """{"options":[]}""",
        ).forEach { string ->
            test("Decode '$string'") {
                shouldThrow<Exception> {
                    Json.decodeFromString<OptionSet>(string)
                }
            }
        }
    }

    context("Encode & decode empty options") {
        val optionSet = buildOptionSet { }
        val json = buildJsonObject {
            putJsonObject("options") {}
        }

        test("Encode") {
            val encoded = Json.encodeToJsonElement(optionSet)
            encoded shouldBe json
        }

        test("Decode") {
            val decoded = Json.decodeFromJsonElement<OptionSet>(json)
            decoded shouldBe optionSet
        }
    }

    context("Encode & decode 3 options") {
        val optionSet = buildOptionSet {
            put(opt1, 123)
            put(opt2, "hello world")
            put(opt3, listOf(1, 2, 3))
        }
        val json = buildJsonObject {
            putJsonObject("options") {
                put("opt_int", 123)
                put("opt_string", "hello world")
                putJsonArray("opt_int_array") {
                    add(1)
                    add(2)
                    add(3)
                }
            }
        }

        test("Encode") {
            val encoded = Json.encodeToJsonElement(optionSet)
            encoded shouldBe json
        }

        test("Decode") {
            val decoded = Json.decodeFromJsonElement<OptionSet>(json)
            decoded shouldBe optionSet
        }
    }
})
