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

/**
 * Access point information used in client mode.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._list_access_points.html">camera._listAccessPoints · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._set_access_point.html">camera._setAccessPoint · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class AccessPoint {
    /**
     * Security type of an access point
     */
    public enum Security {
        /**
         * No security.
         */
        @SerializedName("none") NONE,

        /**
         * WEP
         */
        @SerializedName("WEP") WEP,

        /**
         * WPA/WPA2 PSK
         */
        @SerializedName("WPA/WPA2 PSK") WPA_WPA2_PSK
    }

    /**
     * IP address allocation type of an access point
     */
    public enum IpAddressAllocation {
        /**
         * Allocate IP address by DHCP.
         */
        @SerializedName("dynamic") DYNAMIC,

        /**
         * Allocate IP address statically.
         */
        @SerializedName("static") STATIC
    }

    private final String ssid;

    @SerializedName("ssidStealth")
    private final Boolean isStealth;

    private final Security security;

    private final String password;

    private final Integer connectionPriority;

    private final IpAddressAllocation ipAddressAllocation;

    private final String ipAddress;

    private final String subnetMask;

    private final String defaultGateway;

    private AccessPoint(
            @Nonnull final String ssid,
            @Nonnull final Security security,
            @Nullable final Boolean isStealth,
            @Nullable final String password,
            @Nullable final Integer connectionPriority,
            @Nullable final IpAddressAllocation ipAddressAllocation,
            @Nullable final String ipAddress,
            @Nullable final String subnetMask,
            @Nullable final String defaultGateway
    ) {
        this.ssid = ssid;
        this.security = security;
        this.isStealth = isStealth;
        this.password = password;
        this.connectionPriority = connectionPriority;
        this.ipAddressAllocation = ipAddressAllocation;
        this.ipAddress = ipAddress;
        this.subnetMask = subnetMask;
        this.defaultGateway = defaultGateway;
    }

    /**
     * SSID
     */
    @Nonnull
    public String getSsid() {
        return ssid;
    }

    /**
     * SSID is stealth if true
     */
    public boolean isStealth() {
        return isStealth;
    }

    /**
     * Authentication mode
     */
    @Nonnull
    public Security getSecurity() {
        return security;
    }

    /**
     * Connection priority (1 to 5). The default is 1.
     */
    public int getConnectionPriority() {
        return connectionPriority;
    }

    /**
     * IP address allocation type. The default is {@link IpAddressAllocation#DYNAMIC}.
     */
    @Nonnull
    public IpAddressAllocation getIpAddressAllocation() {
        return ipAddressAllocation;
    }

    /**
     * IP address assigned to camera. This is null if IpAddressAllocation is not {@link IpAddressAllocation#STATIC}.
     */
    @Nullable
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Subnet mask. This is null if IpAddressAllocation is not {@link IpAddressAllocation#STATIC}.
     */
    @Nullable
    public String getSubnetMask() {
        return subnetMask;
    }

    /**
     * Default gateway. This is null if IpAddressAllocation is not {@link IpAddressAllocation#STATIC}.
     */
    @Nullable
    public String getDefaultGateway() {
        return defaultGateway;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessPoint that = (AccessPoint) o;
        return ssid.equals(that.ssid) &&
                Objects.equals(isStealth, that.isStealth) &&
                security == that.security &&
                Objects.equals(password, that.password) &&
                Objects.equals(connectionPriority, that.connectionPriority) &&
                ipAddressAllocation == that.ipAddressAllocation &&
                Objects.equals(ipAddress, that.ipAddress) &&
                Objects.equals(subnetMask, that.subnetMask) &&
                Objects.equals(defaultGateway, that.defaultGateway);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(ssid, isStealth, security, password, connectionPriority, ipAddressAllocation, ipAddress, subnetMask, defaultGateway);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccessPoint{");
        sb.append("ssid='").append(ssid).append('\'');
        sb.append(", isStealth=").append(isStealth);
        sb.append(", security=").append(security);
        sb.append(", password='").append(password).append('\'');
        sb.append(", connectionPriority=").append(connectionPriority);
        sb.append(", ipAddressAllocation=").append(ipAddressAllocation);
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append(", subnetMask='").append(subnetMask).append('\'');
        sb.append(", defaultGateway='").append(defaultGateway).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Builder class of {@link AccessPoint}
     */
    public static final class Builder {
        private final String ssid;

        private Security security;

        private Boolean isStealth;

        private String password;

        private Integer connectionPriority;

        private IpAddressAllocation ipAddressAllocation;

        private String ipAddress;

        private String subnetMask;

        private String defaultGateway;

        /**
         * Main constructor
         *
         * @param ssid SSID of the access point
         * @throws NullPointerException if ssid is null.
         */
        public Builder(@Nonnull final String ssid) {
            this.ssid = Objects.requireNonNull(ssid, "ssid can not be null.");
            this.security = Security.NONE;
        }

        /**
         * Set the security type and the password.
         *
         * @param security Security method to set.
         * @param password Passphrase of this access point. This is required if the security is not {@link Security#NONE}.
         * @return Builder instance.
         * @throws NullPointerException if the password is null and security is not {@link Security#NONE}.
         */
        @Nonnull
        public Builder security(@Nonnull final Security security, @Nullable final String password) {
            if (security != Security.NONE && password == null) {
                throw new NullPointerException("password can not be null, when security is not 'NONE'");
            }

            this.security = security;
            this.password = password;
            return this;
        }

        /**
         * Set isStealth property
         *
         * @param isStealth true if the access point is stealth.
         * @return Builder instance.
         */
        @Nonnull
        public Builder isStealth(final boolean isStealth) {
            this.isStealth = isStealth;
            return this;
        }

        /**
         * Set connection property.
         *
         * @param connectionPriority Connection priority to set.
         * @return Builder instance.
         */
        @Nonnull
        public Builder connectionPriority(final int connectionPriority) {
            this.connectionPriority = connectionPriority;
            return this;
        }

        /**
         * Set IP address, subnet mask and default gateway, and set allocation method {@link IpAddressAllocation#STATIC}.
         *
         * @param ipAddress      IP address to set. For example, "192.168.1.15"
         * @param subnetMask     Subnet mask to set. For example, "255.255.255.0".
         * @param defaultGateway Default gateway to set. For example, "192.168.1.1"
         * @return Builder instance.
         * @throws NullPointerException if ipAddress, subnetMask, or defaultGateway is null.
         */
        @Nonnull
        public Builder ipAddress(@Nonnull final String ipAddress, @Nonnull final String subnetMask, @Nonnull final String defaultGateway) {
            this.ipAddressAllocation = IpAddressAllocation.STATIC;
            this.ipAddress = Objects.requireNonNull(ipAddress, "ipAddress can not be null.");
            this.subnetMask = Objects.requireNonNull(subnetMask, "subnetMask can not be null.");
            this.defaultGateway = Objects.requireNonNull(defaultGateway, "defaultGateway can not be null.");
            return this;
        }

        /**
         * returns the created {@link AccessPoint} instance.
         */
        @Nonnull
        public AccessPoint build() {
            return new AccessPoint(
                    ssid,
                    security,
                    isStealth,
                    password,
                    connectionPriority,
                    ipAddressAllocation,
                    ipAddress,
                    subnetMask,
                    defaultGateway
            );
        }
    }
}
