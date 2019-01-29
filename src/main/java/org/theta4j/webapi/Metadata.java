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
 * Metadata of image file.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._get_metadata.html">camera._getMetadata · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class Metadata {
    private final Exif exif;

    private final XMP xmp;

    /**
     * Exif data.
     */
    public Exif getExif() {
        return exif;
    }

    /**
     * Photo Sphere XMP data.
     */
    public XMP getXmp() {
        return xmp;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(exif, metadata.exif) &&
                Objects.equals(xmp, metadata.xmp);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(exif, xmp);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("Metadata{");
        sb.append("exif=").append(exif);
        sb.append(", xmp=").append(xmp);
        sb.append('}');
        return sb.toString();
    }

    /**
     * for GSON
     */
    private Metadata(final Exif exif, final XMP xmp) {
        this.exif = exif;
        this.xmp = xmp;
    }
}
