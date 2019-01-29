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
import java.math.BigDecimal;
import java.util.Objects;

/**
 * GPS location information.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/gps_info.html">gpsInfo · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class GpsInfo {
    @SerializedName("lat")
    private final BigDecimal latitude;

    @SerializedName("lng")
    private final BigDecimal longitude;

    @SerializedName("_altitude")
    private final BigDecimal altitude;

    @SerializedName("_dateTimeZone")
    private final String dateTimeZone;

    @SerializedName("_datum")
    private final String datum;

    public GpsInfo(
            @Nonnull final BigDecimal latitude,
            @Nonnull final BigDecimal longitude,
            @Nonnull final BigDecimal altitude,
            @Nonnull final String dateTimeZone,
            @Nonnull final String datum) {
        this.latitude = Objects.requireNonNull(latitude, "latitude can not be null.");
        this.longitude = Objects.requireNonNull(longitude, "longitude can not be null.");
        this.altitude = Objects.requireNonNull(altitude, "altitude can not be null.");
        this.dateTimeZone = Objects.requireNonNull(dateTimeZone);
        this.datum = Objects.requireNonNull(datum);
    }

    /**
     * Latitude.
     */
    @Nullable
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * Longitude.
     */
    @Nullable
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * Altitude.
     */
    @Nullable
    public BigDecimal getAltitude() {
        return altitude;
    }

    /**
     * Date time zone.
     */
    @Nullable
    public String getDateTimeZone() {
        return dateTimeZone;
    }

    /**
     * Geodetic reference.
     */
    @Nullable
    public String getDatum() {
        return datum;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GpsInfo gpsInfo = (GpsInfo) o;
        return latitude.equals(gpsInfo.latitude) &&
                longitude.equals(gpsInfo.longitude) &&
                altitude.equals(gpsInfo.altitude) &&
                dateTimeZone.equals(gpsInfo.dateTimeZone) &&
                datum.equals(gpsInfo.datum);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, altitude, dateTimeZone, datum);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("GpsInfo{");
        sb.append("latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", altitude=").append(altitude);
        sb.append(", dateTimeZone='").append(dateTimeZone).append('\'');
        sb.append(", datum='").append(datum).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
