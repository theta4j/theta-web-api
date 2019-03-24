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
import java.util.Objects;
import java.util.Optional;

/**
 * Image format used in shooting.
 * Actual supported values are different between each models.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/file_format.html">fileFormat · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class FileFormat {
    /**
     * Type of file.
     */
    public enum Type {
        /**
         * JPEG
         */
        @SerializedName("jpeg") JPEG,

        /**
         * RAW+
         */
        @SerializedName("raw+") RAW,

        /**
         * MPEG-4
         */
        @SerializedName("mp4") MPEG4
    }

    /**
     * JPEG 5376x2688
     */
    public static final FileFormat JPEG_5376_2688 = new FileFormat(Type.JPEG, 5376, 2688);

    /**
     * JPEG 2048x1024
     */
    public static final FileFormat JPEG_2048_1024 = new FileFormat(Type.JPEG, 2048, 1024);

    /**
     * RAW+ 6720x3360
     */
    public static final FileFormat RAW_6720_3360 = new FileFormat(Type.RAW, 6720, 3360);

    /**
     * MPEG-4 3840x1920 H.264/MPEG-4 AVC
     */
    public static final FileFormat MP4_3840_1920_H264AVC = new FileFormat(Type.MPEG4, 3840, 1920, "H.264/MPEG-4 AVC");

    /**
     * MPEG-4 1920x960 H.264/MPEG-4 AVC
     */
    public static final FileFormat MP4_1920_960_H264AVC = new FileFormat(Type.MPEG4, 1920, 960, "H.264/MPEG-4 AVC");

    /**
     * MPEG-4 1920x1080
     */
    public static final FileFormat MP4_1920_1080 = new FileFormat(Type.MPEG4, 1920, 1080);

    /**
     * MPEG-4 1280x720
     */
    public static final FileFormat MP4_1280_720 = new FileFormat(Type.MPEG4, 1280, 720);

    private Type type;

    private int width;

    private int height;

    @SerializedName("_codec")
    private String codec;

    private FileFormat(@Nonnull final Type type, final int width, final int height) {
        this(type, width, height, null);
    }

    private FileFormat(@Nonnull final Type type, final int width, final int height, @Nullable final String codec) {
        this.type = type;
        this.width = width;
        this.height = height;
        this.codec = codec;
    }

    /**
     * Type of file.
     */
    @Nonnull
    public Type getType() {
        return type;
    }

    /**
     * Image width (px).
     */
    public int getWidth() {
        return width;
    }

    /**
     * Image height (px).
     */
    public int getHeight() {
        return height;
    }

    /**
     * Codec name.
     */
    @Nonnull
    public Optional<String> getCodec() {
        return Optional.ofNullable(codec);
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileFormat that = (FileFormat) o;
        return width == that.width &&
                height == that.height &&
                type == that.type &&
                Objects.equals(codec, that.codec);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, width, height, codec);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileFormat{");
        sb.append("type=").append(type);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", codec='").append(codec).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
