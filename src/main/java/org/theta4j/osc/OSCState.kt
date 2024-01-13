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

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

data class OSCState<T> internal constructor(
        val fingerprint: String,
        val state: T,
) {
    companion object {
        private val format = Json { ignoreUnknownKeys = true }

        fun <T> decode(serializer: KSerializer<T>, obj: JsonObject): OSCState<T> {
            val fingerprint = obj["fingerprint"]!!.jsonPrimitive.content
            val state = format.decodeFromJsonElement(serializer, obj["state"]!!)
            return OSCState(fingerprint, state)
        }
    }
}
