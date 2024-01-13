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
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(ApertureSerializer::class)
data class Aperture(val value: Double) : Comparable<Aperture> {
    companion object {
        val AUTO = Aperture(0.0)
    }

    override fun compareTo(other: Aperture): Int = value.compareTo(other.value)

    override fun toString() = if (this == AUTO) "Auto" else "F$value"
}

private object ApertureSerializer : KSerializer<Aperture> {
    override val descriptor = PrimitiveSerialDescriptor(Aperture::class.simpleName!!, PrimitiveKind.DOUBLE)

    override fun deserialize(decoder: Decoder) = Aperture(decoder.decodeDouble())

    override fun serialize(encoder: Encoder, value: Aperture) {
        encoder.encodeDouble(value.value)
    }
}
