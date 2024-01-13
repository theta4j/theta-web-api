/*
 * Copyright (C) 2022 theta4j project
 *
 * Licensed under the Apache License Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing software
 * distributed under the License is distributed on an "AS IS" BASIS
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theta4j.webapi

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(IsoSerializer::class)
data class Iso(val value: Int) {
    companion object {
        val AUTO = Iso(0)
    }

    override fun toString(): String = "ISO$value"
}

private object IsoSerializer : KSerializer<Iso> {
    override val descriptor = PrimitiveSerialDescriptor(Iso::class.simpleName!!, PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder) = Iso(decoder.decodeInt())

    override fun serialize(encoder: Encoder, value: Iso) {
        encoder.encodeInt(value.value)
    }
}
