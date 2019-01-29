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
 * camera._listPlugins command.
 */
public final class ListPlugins {
    /**
     * Result of camera._listPlugins command.
     */
    public static final class Result {
        private final List<PluginInfo> plugins;

        /**
         * List of plug-in information.
         */
        @Nonnull
        public List<PluginInfo> getPlugins() {
            return plugins;
        }

        /**
         * for GSON
         */
        private Result(final List<PluginInfo> plugins) {
            this.plugins = plugins;
        }
    }

    private ListPlugins() {
        throw new AssertionError();
    }
}
