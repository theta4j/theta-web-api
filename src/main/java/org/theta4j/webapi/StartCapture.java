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

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * camera.startCapture command.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.start_capture.html">camera.startCapture · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class StartCapture {
    /**
     * Capture mode.
     */
    public enum CaptureMode {
        /**
         * Interval shooting.
         */
        @SerializedName("interval") INTERVAL,

        /**
         * Composite shooting.
         */
        @SerializedName("composite") COMPOSITE,

        /**
         * Auto bracket shooting.
         */
        @SerializedName("bracket") BRACKET,

        /**
         * Time shit shooting.
         */
        @SerializedName("timeShift") TIME_SHIFT;
    }

    static final class Parameter {
        @SerializedName("_mode")
        private final CaptureMode mode;

        Parameter(@Nonnull final CaptureMode mode) {
            this.mode = Objects.requireNonNull(mode, "mode can not be null.");
        }
    }

    /**
     * Result of camera.startCapture command.
     */
    public static final class Result {
        private final List<URL> fileUrls;

        /**
         * List of captured file URLs.
         */
        @Nonnull
        public List<URL> getFileUrls() {
            return fileUrls;
        }

        /**
         * for GSON
         */
        private Result(final List<URL> fileUrls) {
            this.fileUrls = fileUrls;
        }
    }

    private StartCapture() {
        throw new AssertionError();
    }
}
