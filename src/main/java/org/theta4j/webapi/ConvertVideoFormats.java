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
import java.util.Objects;

/**
 * ConvertVideoFormats defines classes for camera._convertVideoFormats command.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._convert_video_formats.html">camera._convertVideoFormats · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class ConvertVideoFormats {
    /**
     * Video dimensions.
     */
    public enum Size {
        /**
         * 3840x1920
         */
        @SerializedName("3840x1920") _3840_1920,

        /**
         * 1920x960
         */
        @SerializedName("1920x960") _1920_960,
    }

    /**
     * Projection type.
     */
    public enum ProjectionType {
        /**
         * Equirectangular
         */
        @SerializedName("Equirectangular") EQUIRECTANGULAR
    }

    /**
     * Video codec.
     */
    public enum Codec {
        /**
         * H.264/MPEG-4 AVC
         */
        @SerializedName("H.264/MPEG-4 AVC") H264_MPEG4_AVC
    }

    /**
     * Top/bottom correction
     */
    public enum TopBottomCorrectionType {
        /**
         * Top/bottom correction enabled and rotational shake correction is enabled.
         */
        @SerializedName("Apply") APPLY,

        /**
         * Both of Top/Bottom correction and rotational shake perfect correction is enabled.
         */
        @SerializedName("ApplyFixedDirection") APPLY_FIXED_DIRECTION,

        /**
         * Top/bottom correction is disabled.
         */
        @SerializedName("Disapply") DISAPPLY
    }

    /**
     * Parameter class of camera._convertVideoFormats command.
     */
    public static final class Parameter {
        private final URL fileUrl;

        private final Size size;

        private final ProjectionType projectionType;

        private final Codec codec;

        @SerializedName("topBottomCorrection")
        private final TopBottomCorrectionType topBottomCorrectionType;

        /**
         * Create new parameter.
         *
         * @param fileUrl                 file URL of the video file to convert.
         * @param size                    dimensional size.
         * @param projectionType          projection type.
         * @param codec                   codec.
         * @param topBottomCorrectionType new Top/Bottom correction type.
         * @throws NullPointerException if the argument is null.
         */
        public Parameter(
                @Nonnull final URL fileUrl,
                @Nonnull final Size size,
                @Nonnull final ProjectionType projectionType,
                @Nonnull final Codec codec,
                @Nonnull final TopBottomCorrectionType topBottomCorrectionType) {
            this.fileUrl = Objects.requireNonNull(fileUrl, "fileUrl can not be null.");
            this.size = Objects.requireNonNull(size, "size can not be null.");
            this.projectionType = Objects.requireNonNull(projectionType, "projectionType can not be null.");
            this.codec = Objects.requireNonNull(codec, "codec can not be null.");
            this.topBottomCorrectionType = Objects.requireNonNull(topBottomCorrectionType, "topBottomCorrectionType can not be null.");
        }

        /**
         * File URL of the video file to convert.
         */
        @Nonnull
        public URL getFileUrl() {
            return fileUrl;
        }

        /**
         * Dimensional size.
         */
        @Nonnull
        public Size getSize() {
            return size;
        }

        /**
         * Projection type.
         */
        @Nonnull
        public ProjectionType getProjectionType() {
            return projectionType;
        }

        /**
         * Video codec.
         */
        @Nonnull
        public Codec getCodec() {
            return codec;
        }

        /**
         * Top/Bottom correction type.
         */
        @Nonnull
        public TopBottomCorrectionType getTopBottomCorrectionType() {
            return topBottomCorrectionType;
        }
    }

    /**
     * Result class of camera._convertVideoFormats command.
     */
    public static final class Result {
        private final URL fileUrl;

        /**
         * File URL of the converted video file.
         */
        @Nonnull
        public URL getFileUrl() {
            return fileUrl;
        }

        /**
         * for GSON
         */
        private Result(final URL fileUrl) {
            this.fileUrl = fileUrl;
        }
    }

    private ConvertVideoFormats() {
        throw new AssertionError();
    }
}
