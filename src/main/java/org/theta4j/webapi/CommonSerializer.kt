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
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

internal object IntAsDoubleSerializer : KSerializer<Int> {
    override val descriptor = PrimitiveSerialDescriptor("doubleValue", PrimitiveKind.DOUBLE)

    override fun deserialize(decoder: Decoder): Int = decoder.decodeDouble().roundToInt()

    override fun serialize(encoder: Encoder, value: Int) {
        encoder.encodeDouble(value.toDouble())
    }
}

/**
 * Serializer for the date time string like '2022:02:26 19:27:44+09:00'
 */
internal object DateTimeZoneSerializer : KSerializer<OffsetDateTime> {

    private val fmt = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ssXXX")

    override val descriptor = PrimitiveSerialDescriptor(OffsetDateTime::class.simpleName!!, PrimitiveKind.STRING)

    /**
     * Deserialize from the string like '2022:02:26 19:27:44+09:00'
     * Returns null when the string is empty.
     */
    override fun deserialize(decoder: Decoder): OffsetDateTime {
        val string = decoder.decodeString()
        return OffsetDateTime.parse(string, fmt)
    }

    /**
     * Serialize to the string like '2022:02:26 19:27:44+09:00'
     * Encodes empty string when the [value] is null.
     */
    override fun serialize(encoder: Encoder, value: OffsetDateTime) {
        val string = fmt.format(value)
        encoder.encodeString(string)
    }
}

/**
 * Serializer for the date time string like '2022:02:26 19:27:44'
 */
internal object DateTimeSerializer : KSerializer<LocalDateTime> {

    private val fmt = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")

    override val descriptor = PrimitiveSerialDescriptor(LocalDateTime::class.simpleName!!, PrimitiveKind.STRING)

    /**
     * Deserialize from the string like '2022:02:26 19:27:44'
     * Returns null when the string is empty.
     */
    override fun deserialize(decoder: Decoder): LocalDateTime {
        val string = decoder.decodeString()
        return LocalDateTime.parse(string, fmt)
    }

    /**
     * Serialize to the string like '2022:02:26 19:27:44'
     * Encodes empty string when the [value] is null.
     */
    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val string = fmt.format(value)
        encoder.encodeString(string)
    }
}
