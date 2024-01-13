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

@Serializable(WlanChannelSerializer::class)
data class WlanChannel(internal val value: Int) {
    companion object {
        val NOT_SPECIFIED = WlanChannel(0)
        val CH1 = WlanChannel(1)
        val CH6 = WlanChannel(6)
        val CH11 = WlanChannel(11)
    }
}

private object WlanChannelSerializer : KSerializer<WlanChannel> {
    override val descriptor = PrimitiveSerialDescriptor(WlanChannelSerializer::class.java.simpleName, PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): WlanChannel = WlanChannel(decoder.decodeInt())

    override fun serialize(encoder: Encoder, value: WlanChannel) {
        encoder.encodeInt(value.value)
    }
}
