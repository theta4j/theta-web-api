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

package org.theta4j.webapi;

import javax.annotation.Nonnull;
import java.util.Objects;

final class DeleteAccessPoint {
    static final class Parameter {
        private final String ssid;

        Parameter(@Nonnull final String ssid) {
            this.ssid = Objects.requireNonNull(ssid, "ssid can not be null.");
        }
    }

    private DeleteAccessPoint() {
        throw new AssertionError();
    }
}
