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

/**
 * Format of live view.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/preview_format.html">previewFormat · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class PreviewFormat {
    /**
     * 1920x960 8FPS
     */
    public static final PreviewFormat _1920_960_8P = new PreviewFormat(1920, 960, 8);

    /**
     * 1024x512 30FPS
     */
    public static final PreviewFormat _1024_512_30P = new PreviewFormat(1024, 512, 30);

    /**
     * 1024x512 8FPS
     */
    public static final PreviewFormat _1024_512_5P = new PreviewFormat(1024, 512, 8);

    /**
     * 640x320 30FPS
     */
    public static final PreviewFormat _640_320_30P = new PreviewFormat(640, 320, 30);

    /**
     * 640x320 10FPS
     */
    public static final PreviewFormat _640_320_10P = new PreviewFormat(640, 320, 10);

    /**
     * 640x320 8FPS
     */
    public static final PreviewFormat _640_320_8P = new PreviewFormat(640, 320, 8);

    private final int width;

    private final int height;

    private final int framerate;

    /**
     * @param width     Image width (px).
     * @param height    Image height (px).
     * @param framerate Frames per sec.
     */
    public PreviewFormat(final int width, final int height, final int framerate) {
        this.width = width;
        this.height = height;
        this.framerate = framerate;
    }

    /**
     * Image width in pixels.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Image height in pixels.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Frames per sec.
     */
    public int getFramerate() {
        return framerate;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreviewFormat that = (PreviewFormat) o;
        return width == that.width &&
                height == that.height &&
                framerate == that.framerate;
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(width, height, framerate);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("PreviewFormat{");
        sb.append("width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", framerate=").append(framerate);
        sb.append('}');
        return sb.toString();
    }
}
