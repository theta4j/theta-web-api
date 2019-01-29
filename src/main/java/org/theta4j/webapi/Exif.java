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

import java.math.BigDecimal;

/**
 * Exif data.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._get_metadata.html">camera._getMetadata · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class Exif {
    @SerializedName("ExifVersion")
    private String exifVersion;

    @SerializedName("ImageDescription")
    private String imageDescription;

    @SerializedName("DateTime")
    private String dateTime;

    @SerializedName("ImageWidth")
    private int imageWidth;

    @SerializedName("ImageLength")
    private int imageLength;

    @SerializedName("ColorSpace")
    private int colorSpace;

    @SerializedName("Compression")
    private int compression;

    @SerializedName("Orientation")
    private int orientation;

    @SerializedName("Flash")
    private int flash;

    @SerializedName("FocalLength")
    private BigDecimal focalLength;

    @SerializedName("WhiteBalance")
    private int whiteBalance;

    @SerializedName("ExposureTime")
    private BigDecimal exposureTime;

    @SerializedName("FNumber")
    private BigDecimal fNumber;

    @SerializedName("ExposureProgram")
    private int exposureProgram;

    @SerializedName("PhotographicSensitivity")
    private int photographicSensitivity;

    @SerializedName("ApertureValue")
    private BigDecimal apertureValue;

    @SerializedName("BrightnessValue")
    private BigDecimal brightnessValue;

    @SerializedName("ExposureBiasValue")
    private BigDecimal exposureBiasValue;

    @SerializedName("GPSLatitudeRef")
    private String gpsLatitudeRef;

    @SerializedName("GPSLatitude")
    private BigDecimal gpsLatitude;

    @SerializedName("GPSLongitudeRef")
    private String gpsLongitudeRef;

    @SerializedName("GPSLongitude")
    private BigDecimal gpsLongitude;

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("Software")
    private String software;

    @SerializedName("Copyright")
    private String copyright;

    /**
     * EXIF Support version
     */
    public String getExifVersion() {
        return exifVersion;
    }

    /**
     * Title or name
     */
    public String getImageDescription() {
        return imageDescription;
    }

    /**
     * Timestamp of creation or update.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Width in pixels.
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Height in pixels.
     */
    public int getImageLength() {
        return imageLength;
    }

    /**
     * Color space.
     */
    public int getColorSpace() {
        return colorSpace;
    }

    /**
     * Compression format.
     */
    public int getCompression() {
        return compression;
    }

    /**
     * Image orientation.
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * Flash exposure.
     */
    public int getFlash() {
        return flash;
    }

    /**
     * Focal length.
     */
    public BigDecimal getFocalLength() {
        return focalLength;
    }

    /**
     * White balance.
     */
    public int getWhiteBalance() {
        return whiteBalance;
    }

    /**
     * Exposure time.
     */
    public BigDecimal getExposureTime() {
        return exposureTime;
    }

    /**
     * F-number.
     */
    public BigDecimal getfNumber() {
        return fNumber;
    }

    /**
     * Exposure program.
     */
    public int getExposureProgram() {
        return exposureProgram;
    }

    /**
     * Shooting sensitivity.
     */
    public int getPhotographicSensitivity() {
        return photographicSensitivity;
    }

    /**
     * Aperture value.
     */
    public BigDecimal getApertureValue() {
        return apertureValue;
    }

    /**
     * Brightness value.
     */
    public BigDecimal getBrightnessValue() {
        return brightnessValue;
    }

    /**
     * Exposure compensation value.
     */
    public BigDecimal getExposureBiasValue() {
        return exposureBiasValue;
    }

    /**
     * Latitudinal direction of movement.
     */
    public String getGpsLatitudeRef() {
        return gpsLatitudeRef;
    }

    /**
     * Latitude.
     */
    public BigDecimal getGpsLatitude() {
        return gpsLatitude;
    }

    /**
     * Longitudinal direction of movement.
     */
    public String getGpsLongitudeRef() {
        return gpsLongitudeRef;
    }

    /**
     * Longitude.
     */
    public BigDecimal getGpsLongitude() {
        return gpsLongitude;
    }

    /**
     * Camera manufacturer.
     */
    public String getMake() {
        return make;
    }

    /**
     * Camera model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Software name.
     */
    public String getSoftware() {
        return software;
    }

    /**
     * Copyright.
     */
    public String getCopyright() {
        return copyright;
    }
}
