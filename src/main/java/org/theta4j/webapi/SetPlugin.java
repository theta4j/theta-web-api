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

final class SetPlugin {
    static final class Parameter {
        private final String packageName;

        private final boolean boot;

        Parameter(@Nonnull final String packageName, final boolean boot) {
            this.packageName = Objects.requireNonNull(packageName, "packageName can not be null.");
            this.boot = boot;
        }
    }

    private SetPlugin() {
        throw new AssertionError();
    }
}
