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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(AutoBracketProgramSerializer::class)
data class AutoBracketProgram(
        val parameters: List<Parameter>,
) {
    init {
        require(parameters.isNotEmpty()) { "parameters must have 1 or more entries." }
    }

    constructor(parameter: Parameter, vararg parameters: Parameter) : this(listOf(parameter) + parameters.toList())

    @Serializable
    data class Parameter(
            val iso: Iso? = null,

            val shutterSpeed: ShutterSpeed? = null,

            @SerialName("_colorTemperature")
            val colorTemperature: Int? = null,

            val exposureProgram: ExposureProgram? = null,

            val aperture: Aperture? = null,

            val exposureCompensation: ExposureCompensation? = null,

            val whiteBalance: WhiteBalance? = null,
    )
}

private object AutoBracketProgramSerializer : KSerializer<AutoBracketProgram> {
    override val descriptor = buildClassSerialDescriptor(AutoBracketProgram::class.simpleName!!) {
        element("_bracketNumber", Int.serializer().descriptor)
        element("_bracketParameters", ListSerializer(AutoBracketProgram.Parameter.serializer()).descriptor)
    }

    override fun deserialize(decoder: Decoder): AutoBracketProgram {
        require(decoder is JsonDecoder)
        val jsonParameters = decoder.decodeJsonElement().jsonObject["_bracketParameters"]!!.jsonArray
        val parameters = jsonParameters.map { decoder.json.decodeFromJsonElement(AutoBracketProgram.Parameter.serializer(), it) }
        return AutoBracketProgram(parameters)
    }

    override fun serialize(encoder: Encoder, value: AutoBracketProgram) {
        require(encoder is JsonEncoder)
        val jsonObject = buildJsonObject {
            put("_bracketNumber", value.parameters.size)
            putJsonArray("_bracketParameters") {
                value.parameters
                        .map { encoder.json.encodeToJsonElement(AutoBracketProgram.Parameter.serializer(), it) }
                        .forEach(this::add)
            }
        }
        encoder.encodeJsonElement(jsonObject)
    }
}
