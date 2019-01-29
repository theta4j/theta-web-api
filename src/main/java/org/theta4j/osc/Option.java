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
 * Option represents OSC option definition.
 *
 * @param <T> Type of option value.
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/options">Options | Open Spherical Camera API</a>
 */
public interface Option<T> {
    /**
     * Suffix of option support name, "Support".
     */
    @Nonnull
    String SUPPORT = "Support";

    /**
     * Option name.
     */
    @Nonnull
    String getName();

    /**
     * Type of option value.
     */
    @Nonnull
    Class<T> getType();

    /**
     * Create new option definition.
     *
     * @param name Option name.
     * @param type Type of option value.
     * @param <T>  Type of option value.
     * @return Created option definition.
     */
    @Nonnull
    static <T> Option<T> create(@Nonnull final String name, @Nonnull final Class<T> type) {
        Objects.requireNonNull(name, "name can not be null.");
        Objects.requireNonNull(type, "type can not be null.");

        return new OptionImpl<>(name, type);
    }
}
