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
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(OptionSetSerializer::class)
data class OptionSet internal constructor(
        internal val data: Map<String, JsonElement> = emptyMap(),
) {
    companion object {
        private val format = Json { ignoreUnknownKeys = true }
    }

    operator fun <T> get(option: Option<T>): T? {
        val value = data[option.name] ?: return null
        return format.decodeFromJsonElement(option.serializer, value)
    }

    operator fun <T> get(option: ArrayOption<T>): List<T>? {
        val value = data[option.name] ?: return null
        return value.jsonArray.map { format.decodeFromJsonElement(option.serializer, it) }
    }
}

//
// Builder
//

class OptionSetBuilder internal constructor() {
    private val data = mutableMapOf<String, JsonElement>()

    fun <T> put(option: Option<T>, value: T) {
        data[option.name] = Json.encodeToJsonElement(option.serializer, value)
    }

    fun <T> put(option: ArrayOption<T>, value: List<T>) {
        data[option.name] = buildJsonArray {
            value.map { Json.encodeToJsonElement(option.serializer, it) }.forEach(this::add)
        }
    }

    fun build() = OptionSet(data)
}

fun buildOptionSet(block: OptionSetBuilder.() -> Unit): OptionSet = OptionSetBuilder().apply(block).build()

//
// Serializers
//

internal object OptionSetSerializer : KSerializer<OptionSet> {
    override val descriptor = buildClassSerialDescriptor(OptionSet::class.simpleName!!) {
        element("options", ListSerializer(JsonElement.serializer()).descriptor)
    }

    override fun deserialize(decoder: Decoder): OptionSet {
        require(decoder is JsonDecoder)
        val data = decoder.decodeJsonElement().jsonObject
        return OptionSet(data)
    }

    override fun serialize(encoder: Encoder, value: OptionSet) {
        require(encoder is JsonEncoder)
        val jsonObject = buildJsonObject {
            value.data.forEach(this::put)
        }
        encoder.encodeJsonElement(jsonObject)
    }
}
