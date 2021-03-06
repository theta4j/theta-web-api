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
import java.util.List;

/**
 * camera._listAccessPoint command
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._list_access_points.html">camera._listAccessPoints · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class ListAccessPoints {
    /**
     * Result of camera._listAccessPoint command.
     */
    public static final class Result {
        private final List<AccessPoint> accessPoints;

        /**
         * Access point list.
         */
        @Nonnull
        public List<AccessPoint> getAccessPoints() {
            return accessPoints;
        }

        /**
         * for GSON
         */
        private Result(final List<AccessPoint> accessPoints) {
            this.accessPoints = accessPoints;
        }
    }

    private ListAccessPoints() {
        throw new AssertionError();
    }
}
