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

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject

@Serializable
data class Metadata internal constructor(
        val exif: Exif,

        @Serializable(XmpOrNullSerializer::class)
        val xmp: XMP?,
)

private object XmpOrNullSerializer : KSerializer<XMP?> {
    override val descriptor = XMP.serializer().descriptor

    override fun deserialize(decoder: Decoder): XMP? {
        require(decoder is JsonDecoder)
        val json = decoder.decodeJsonElement() as JsonObject
        if (json.keys.isEmpty()) return null
        return XMP.serializer().deserialize(decoder)
    }

    override fun serialize(encoder: Encoder, value: XMP?) {
        require(encoder is JsonEncoder)
        if (value == null) {
            encoder.encodeJsonElement(JsonNull)
        } else {
            encoder.encodeSerializableValue(XMP.serializer(), value)
        }
    }
}
