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

import java.util.Collection;
import java.util.Collections;

final class GetOptions {
    static final class Parameter {
        private final Collection<String> optionNames;

        Parameter(Collection<String> optionNames) {
            this.optionNames = Collections.unmodifiableCollection(optionNames);
        }
    }

    private GetOptions() {
        throw new AssertionError();
    }
}
