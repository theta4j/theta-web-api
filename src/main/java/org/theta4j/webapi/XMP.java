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
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Photo Sphere XMP
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._get_metadata.html">camera._getMetadata · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 * @see <a href="https://developers.google.com/streetview/spherical-metadata">Photo Sphere XMP Metadata  |  Street View  |  Google Developers</a>
 */
public final class XMP {
    /**
     * Projection type.
     */
    public enum ProjectionType {
        @SerializedName("equirectangular") EQUIRECTANGULAR
    }

    @SerializedName("ProjectionType")
    private final ProjectionType projectionType;

    @SerializedName("UsePanoramaViewer")
    private final boolean usePanoramaViewer;

    @SerializedName("PoseHeadingDegrees")
    private final BigDecimal poseHeadingDegrees;

    @SerializedName("CroppedAreaImageWidthPixels")
    private final int croppedAreaImageWidthPixels;

    @SerializedName("CroppedAreaImageHeightPixels")
    private final int croppedAreaImageHeightPixels;

    @SerializedName("FullPanoWidthPixels")
    private final int fullPanoWidthPixels;

    @SerializedName("FullPanoHeightPixels")
    private final int fullPanoHeightPixels;

    @SerializedName("CroppedAreaLeftPixels")
    private final int croppedAreaLeftPixels;

    @SerializedName("CroppedAreaTopPixels")
    private final int croppedAreaTopPixels;

    /**
     * Projection type.
     */
    public ProjectionType getProjectionType() {
        return projectionType;
    }

    /**
     * Whether to display using the panorama viewer.
     */
    public boolean usePanoramaViewer() {
        return usePanoramaViewer;
    }

    /**
     * Compass heading, for the center the image.
     */
    public BigDecimal getPoseHeadingDegrees() {
        return poseHeadingDegrees;
    }

    /**
     * Actual image width in pixels.
     */
    public int getCroppedAreaImageWidthPixels() {
        return croppedAreaImageWidthPixels;
    }

    /**
     * Actual image height in pixels.
     */
    public int getCroppedAreaImageHeightPixels() {
        return croppedAreaImageHeightPixels;
    }

    /**
     * Width in pixels when the actual image size is based on a panoramic image.
     */
    public int getFullPanoWidthPixels() {
        return fullPanoWidthPixels;
    }

    /**
     * Height in pixels when the actual image size is based on a panoramic image
     */
    public int getFullPanoHeightPixels() {
        return fullPanoHeightPixels;
    }

    /**
     * Width in pixels from the panoramic image of the actual image
     */
    public int getCroppedAreaLeftPixels() {
        return croppedAreaLeftPixels;
    }

    /**
     * Height in pixels from the panoramic image of the actual image
     */
    public int getCroppedAreaTopPixels() {
        return croppedAreaTopPixels;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XMP xmp = (XMP) o;
        return usePanoramaViewer == xmp.usePanoramaViewer &&
                croppedAreaImageWidthPixels == xmp.croppedAreaImageWidthPixels &&
                croppedAreaImageHeightPixels == xmp.croppedAreaImageHeightPixels &&
                fullPanoWidthPixels == xmp.fullPanoWidthPixels &&
                fullPanoHeightPixels == xmp.fullPanoHeightPixels &&
                croppedAreaLeftPixels == xmp.croppedAreaLeftPixels &&
                croppedAreaTopPixels == xmp.croppedAreaTopPixels &&
                projectionType == xmp.projectionType &&
                Objects.equals(poseHeadingDegrees, xmp.poseHeadingDegrees);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectionType, usePanoramaViewer, poseHeadingDegrees, croppedAreaImageWidthPixels, croppedAreaImageHeightPixels, fullPanoWidthPixels, fullPanoHeightPixels, croppedAreaLeftPixels, croppedAreaTopPixels);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("XMP{");
        sb.append("projectionType=").append(projectionType);
        sb.append(", usePanoramaViewer=").append(usePanoramaViewer);
        sb.append(", poseHeadingDegrees=").append(poseHeadingDegrees);
        sb.append(", croppedAreaImageWidthPixels=").append(croppedAreaImageWidthPixels);
        sb.append(", croppedAreaImageHeightPixels=").append(croppedAreaImageHeightPixels);
        sb.append(", fullPanoWidthPixels=").append(fullPanoWidthPixels);
        sb.append(", fullPanoHeightPixels=").append(fullPanoHeightPixels);
        sb.append(", croppedAreaLeftPixels=").append(croppedAreaLeftPixels);
        sb.append(", croppedAreaTopPixels=").append(croppedAreaTopPixels);
        sb.append('}');
        return sb.toString();
    }

    /**
     * for GSON
     */
    private XMP(
            final ProjectionType projectionType,
            final boolean usePanoramaViewer,
            final BigDecimal poseHeadingDegrees,
            final int croppedAreaImageWidthPixels,
            final int croppedAreaImageHeightPixels,
            final int fullPanoWidthPixels,
            final int fullPanoHeightPixels,
            final int croppedAreaLeftPixels,
            final int croppedAreaTopPixels
    ) {
        this.projectionType = projectionType;
        this.usePanoramaViewer = usePanoramaViewer;
        this.poseHeadingDegrees = poseHeadingDegrees;
        this.croppedAreaImageWidthPixels = croppedAreaImageWidthPixels;
        this.croppedAreaImageHeightPixels = croppedAreaImageHeightPixels;
        this.fullPanoWidthPixels = fullPanoWidthPixels;
        this.fullPanoHeightPixels = fullPanoHeightPixels;
        this.croppedAreaLeftPixels = croppedAreaLeftPixels;
        this.croppedAreaTopPixels = croppedAreaTopPixels;
    }
}
