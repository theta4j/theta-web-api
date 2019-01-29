/*
 * Copyright (C) 2019 theta4j project
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

package org.theta4j.osc;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Command represents OSC command definition.
 *
 * @param <P> Type of command parameter.
 * @param <R> Type of command result.
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/commands/">Commands | Open Spherical Camera API</a>
 */
public interface Command<P, R> {
    /**
     * Command name.
     */
    @Nonnull
    String getName();

    /**
     * Type of command parameter.
     */
    @Nonnull
    Class<P> getParameterType();

    /**
     * Type of command result.
     */
    @Nonnull
    Class<R> getResultType();

    /**
     * Create new command definition.
     *
     * @param name          Command name.
     * @param parameterType Type of parameter.
     * @param resultType    Type of result.
     * @param <P>           Type of parameter.
     * @param <R>           Type of result.
     * @return created command definition.
     */
    @Nonnull
    static <P, R> Command<P, R> create(@Nonnull final String name, @Nonnull final Class<P> parameterType, @Nonnull final Class<R> resultType) {
        Objects.requireNonNull(name, "name can not be null.");
        Objects.requireNonNull(parameterType, "parameterType can not be null. Consider to use java.lang.Void class instead of null.");
        Objects.requireNonNull(resultType, "resultType can not be null. Consider to use java.lang.Void class instead of null.");

        return new CommandImpl<>(name, parameterType, resultType);
    }
}
