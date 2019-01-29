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
import java.util.List;
import java.util.Objects;

/**
 * ThetaState represents result of GET /osc/info
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/protocols/info.html">Info · protocols · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class ThetaInfo {
    /**
     * Endpoint information.
     */
    public static final class Endpoints {
        private final int httpPort;

        private final int httpUpdatesPort;

        /**
         * HTTP port.
         */
        public int getHttpPort() {
            return httpPort;
        }

        /**
         * HTTP updates port.
         */
        public int getHttpUpdatesPort() {
            return httpUpdatesPort;
        }

        /**
         * for GSON
         */
        private Endpoints(final int httpPort, final int httpUpdatesPort) {
            this.httpPort = httpPort;
            this.httpUpdatesPort = httpUpdatesPort;
        }
    }

    private final String manufacturer;

    private final String model;

    private final String serialNumber;

    @SerializedName("_wlanMacAddress")
    private final String wlanMacAddress;

    @SerializedName("_bluetoothMacAddress")
    private final String bluetoothMacAddress;

    private final String firmwareVersion;

    private final URL supportUrl;

    private final boolean gps;

    private final boolean gyro;

    private final int uptime;

    private final List<String> api;

    private final Endpoints endpoints;

    private final List<ApiVersion> apiLevel;

    /**
     * The camera manufacturer.
     */
    @Nonnull
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * The camera model.
     */
    @Nonnull
    public String getModel() {
        return model;
    }

    /**
     * Serial number.
     */
    @Nonnull
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * MAC address of wireless LAN
     */
    @Nullable
    public String getWlanMacAddress() {
        return wlanMacAddress;
    }

    /**
     * MAC address of Bluetooth
     */
    @Nullable
    public String getBluetoothMacAddress() {
        return bluetoothMacAddress;
    }

    /**
     * Current firmware version.
     */
    @Nonnull
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * URL for the camera’s support webpage.
     */
    @Nonnull
    public URL getSupportUrl() {
        return supportUrl;
    }

    /**
     * True if the camera has GPS.
     */
    public boolean hasGps() {
        return gps;
    }

    /**
     * True if the camera has Gyroscope.
     */
    public boolean hasGyro() {
        return gyro;
    }

    /**
     * Number of seconds since the camera boot.
     */
    public int getUptime() {
        return uptime;
    }

    /**
     * List of supported APIs.
     */
    @Nonnull
    public List<String> getApi() {
        return api;
    }

    /**
     * Camera's endpoints.
     */
    @Nonnull
    public Endpoints getEndpoints() {
        return endpoints;
    }

    /**
     * List of supported APIs
     */
    @Nonnull
    public List<ApiVersion> getApiLevel() {
        return apiLevel;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThetaInfo thetaInfo = (ThetaInfo) o;
        return gps == thetaInfo.gps &&
                gyro == thetaInfo.gyro &&
                uptime == thetaInfo.uptime &&
                Objects.equals(manufacturer, thetaInfo.manufacturer) &&
                Objects.equals(model, thetaInfo.model) &&
                Objects.equals(serialNumber, thetaInfo.serialNumber) &&
                Objects.equals(wlanMacAddress, thetaInfo.wlanMacAddress) &&
                Objects.equals(bluetoothMacAddress, thetaInfo.bluetoothMacAddress) &&
                Objects.equals(firmwareVersion, thetaInfo.firmwareVersion) &&
                Objects.equals(supportUrl, thetaInfo.supportUrl) &&
                Objects.equals(api, thetaInfo.api) &&
                Objects.equals(endpoints, thetaInfo.endpoints) &&
                Objects.equals(apiLevel, thetaInfo.apiLevel);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, serialNumber, wlanMacAddress, bluetoothMacAddress, firmwareVersion, supportUrl, gps, gyro, uptime, api, endpoints, apiLevel);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("ThetaInfo{");
        sb.append("manufacturer='").append(manufacturer).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", serialNumber='").append(serialNumber).append('\'');
        sb.append(", wlanMacAddress='").append(wlanMacAddress).append('\'');
        sb.append(", bluetoothMacAddress='").append(bluetoothMacAddress).append('\'');
        sb.append(", firmwareVersion='").append(firmwareVersion).append('\'');
        sb.append(", supportUrl=").append(supportUrl);
        sb.append(", gps=").append(gps);
        sb.append(", gyro=").append(gyro);
        sb.append(", uptime=").append(uptime);
        sb.append(", api=").append(api);
        sb.append(", endpoints=").append(endpoints);
        sb.append(", apiLevel=").append(apiLevel);
        sb.append('}');
        return sb.toString();
    }

    /**
     * for GSON
     */
    private ThetaInfo(
            final String manufacturer,
            final String model,
            final String serialNumber,
            final String wlanMacAddress,
            final String bluetoothMacAddress,
            final String firmwareVersion,
            final URL supportUrl,
            final boolean gps,
            final boolean gyro,
            final int uptime,
            final List<String> api,
            final Endpoints endpoints,
            final List<ApiVersion> apiLevel
    ) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.serialNumber = serialNumber;
        this.wlanMacAddress = wlanMacAddress;
        this.bluetoothMacAddress = bluetoothMacAddress;
        this.firmwareVersion = firmwareVersion;
        this.supportUrl = supportUrl;
        this.gps = gps;
        this.gyro = gyro;
        this.uptime = uptime;
        this.api = api;
        this.endpoints = endpoints;
        this.apiLevel = apiLevel;
    }
}
