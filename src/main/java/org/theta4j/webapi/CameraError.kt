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

@Serializable(CameraErrorSerializer::class)
data class CameraError(val value: String) {
    companion object {
        val NOT_SUPPORTED_FILE_SYSTEM = CameraError("0x00000040")
        val MEDIA_NOT_READY = CameraError("0x00000100")
        val NOT_ENOUGH_BATTERY = CameraError("0x00000200")
        val INVALID_FILE = CameraError("0x00000400")
        val PLUGIN_BOOT_ERROR = CameraError("0x00000800")
        val IN_PROGRESS_ERROR = CameraError("0x00001000")
        val BATTERY_HIGH_TEMPERATURE = CameraError("0x00200000")
        val CAPTURE_HW_FAILED = CameraError("0x00400000")
        val CAPTURE_SW_FAILED = CameraError("0x00800000")
        val CANT_USE_THIS_CARD = CameraError("0x01000000")
        val FORMAT_INTERNAL_MEM = CameraError("0x02000000")
        val FORMAT_CARD = CameraError("0x04000000")
        val INTERNAL_MEM_ACCESS_FAIL = CameraError("0x08000000")
        val CARD_ACCESS_FAIL = CameraError("0x10000000")
        val UNEXPECTED_ERROR = CameraError("0x20000000")
        val BATTERY_CHARGE_FAIL = CameraError("0x40000000")
        val HIGH_TEMPERATURE = CameraError("0x80000000")
    }
}

private object CameraErrorSerializer : KSerializer<CameraError> {
    override val descriptor = PrimitiveSerialDescriptor(CameraError::class.simpleName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = CameraError(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: CameraError) {
        encoder.encodeString(value.value)
    }
}
