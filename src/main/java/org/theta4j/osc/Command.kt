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

interface Command<P, R> {
    val name: String

    val parameterSerializer: KSerializer<P>

    val resultSerializer: KSerializer<R>

    companion object {
        fun <P, R> create(
                name: String,
                parameterSerializer: KSerializer<P>,
                resultSerializer: KSerializer<R>,
        ): Command<P, R> = CommandImpl(name, parameterSerializer, resultSerializer)
    }
}

private data class CommandImpl<P, R>(
        override val name: String,
        override val parameterSerializer: KSerializer<P>,
        override val resultSerializer: KSerializer<R>,
) : Command<P, R>
