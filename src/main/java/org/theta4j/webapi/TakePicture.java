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
import javax.annotation.Nullable;
import java.net.URL;

/**
 * camera.takePicture command.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.take_picture.html">camera.takePicture · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class TakePicture {
    /**
     * Result of camera.takePicture command.
     */
    public static final class Result {
        private final URL fileUrl;

        @SerializedName("_dngFileUrl")
        private final URL dngFileUrl;

        /**
         * URL of captured image file.
         */
        @Nonnull
        public URL getFileUrl() {
            return fileUrl;
        }

        /**
         * URL of captured RAW image file.
         *
         * @return URL of captured RAW image file. returns null if the file format is not in 'raw+'.
         * @see org.theta4j.webapi.FileFormat
         */
        @Nullable
        public URL getDngFileUrl() {
            return dngFileUrl;
        }

        /**
         * for GSON
         */
        private Result(final URL fileUrl, final URL dngFileUrl) {
            this.fileUrl = fileUrl;
            this.dngFileUrl = dngFileUrl;
        }
    }

    private TakePicture() {
        throw new AssertionError();
    }
}
