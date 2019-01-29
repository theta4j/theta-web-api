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

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.theta4j.osc.GsonUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * ThetaState represents result of POST /osc/state
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/protocols/state.html">State · protocols · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class ThetaState {
    /**
     * Continuous shooting state
     */
    public enum CaptureState {
        /**
         * In standby
         */
        @SerializedName("idle") IDLE,

        /**
         * Performing continuous shooting
         */
        @SerializedName("shooting") SHOOTING,

        /**
         * Self-timer is operating
         */
        @SerializedName("self-timer countdown") SELF_TIMER_COUNTDOWN,

        /**
         * Performing multi bracket shooting
         * Supported on RICOH THETA V and RICOH THETA S firmware v01.82 or later.
         */
        @SerializedName("bracket shooting") BRACKET_SHOOTING
    }

    /**
     * Battery charging state
     */
    public enum BatteryState {
        /**
         * Not charging
         */
        @SerializedName("disconnect") DISCONNECT,

        /**
         * Charging
         */
        @SerializedName("charging") CHARGING,

        /**
         * Charging completed
         */
        @SerializedName("charged") CHARGED
    }

    private final BigDecimal batteryLevel;

    private final URL storageUri;

    @SerializedName("_captureStatus")
    private final CaptureState captureState;

    @SerializedName("_recordedTime")
    private final int recordedTime;

    @SerializedName("_recordableTime")
    private final int recordableTime;

    @SerializedName("_compositeShootingElapsedTime")
    private final int compositeShootingElapsedTime;

    @SerializedName("_latestFileUrl")
    @JsonAdapter(GsonUtils.EmptyStringAsNullAdapter.class)
    private final URL latestFileUrl;

    @SerializedName("_batteryState")
    private final BatteryState batteryState;

    @SerializedName("_apiVersion")
    private final ApiVersion apiVersion;

    @SerializedName("_pluginRunning")
    private final boolean pluginRunning;

    @SerializedName("_pluginWebServer")
    private final boolean pluginWebServer;

    @SerializedName("_cameraError")
    private final List<String> cameraError;

    /**
     * Battery level (0.0 to 1.0)
     */
    @Nonnull
    public BigDecimal getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * Storage URI
     */
    @Nonnull
    public URL getStorageUri() {
        return storageUri;
    }

    /**
     * Continuous shooting state
     */
    @Nonnull
    public CaptureState getCaptureState() {
        return captureState;
    }

    /**
     * Shooting time of movie (sec)
     */
    public int getRecordedTime() {
        return recordedTime;
    }

    /**
     * Remaining time of movie (sec)
     */
    public int getRecordableTime() {
        return recordableTime;
    }

    /**
     * Elapsed time for interval composite shooting (sec)
     * Supported on RICOH THETA V and RICOH THETA S firmware v01.82 or later.
     */
    public int getCompositeShootingElapsedTime() {
        return compositeShootingElapsedTime;
    }

    /**
     * URL of the last saved file
     */
    @Nullable
    public URL getLatestFileUrl() {
        return latestFileUrl;
    }

    /**
     * Charging state
     */
    @Nonnull
    public BatteryState getBatteryState() {
        return batteryState;
    }

    /**
     * API version currently set
     */
    @Nonnull
    public ApiVersion getApiVersion() {
        return apiVersion;
    }

    /**
     * Plugin running state
     * Supported on RICOH THETA V firmware v2.21.1 or later.
     */
    public boolean isPluginRunning() {
        return pluginRunning;
    }

    /**
     * Plugin web server state
     * Supported on RICOH THETA V firmware v2.21.1 or later.
     */
    public boolean hasPluginWebServer() {
        return pluginWebServer;
    }

    /**
     * Error information of the camera
     */
    @Nonnull
    public List<String> getCameraError() {
        return cameraError;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThetaState that = (ThetaState) o;
        return recordedTime == that.recordedTime &&
                recordableTime == that.recordableTime &&
                compositeShootingElapsedTime == that.compositeShootingElapsedTime &&
                pluginRunning == that.pluginRunning &&
                pluginWebServer == that.pluginWebServer &&
                Objects.equals(batteryLevel, that.batteryLevel) &&
                Objects.equals(storageUri, that.storageUri) &&
                captureState == that.captureState &&
                Objects.equals(latestFileUrl, that.latestFileUrl) &&
                batteryState == that.batteryState &&
                apiVersion == that.apiVersion &&
                Objects.equals(cameraError, that.cameraError);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(batteryLevel, storageUri, captureState, recordedTime, recordableTime, compositeShootingElapsedTime, latestFileUrl, batteryState, apiVersion, pluginRunning, pluginWebServer, cameraError);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("ThetaState{");
        sb.append("batteryLevel=").append(batteryLevel);
        sb.append(", storageUri='").append(storageUri).append('\'');
        sb.append(", captureState=").append(captureState);
        sb.append(", recordedTime=").append(recordedTime);
        sb.append(", recordableTime=").append(recordableTime);
        sb.append(", compositeShootingElapsedTime=").append(compositeShootingElapsedTime);
        sb.append(", latestFileUrl='").append(latestFileUrl).append('\'');
        sb.append(", batteryState=").append(batteryState);
        sb.append(", apiVersion=").append(apiVersion);
        sb.append(", pluginRunning=").append(pluginRunning);
        sb.append(", pluginWebServer=").append(pluginWebServer);
        sb.append(", cameraError=").append(cameraError);
        sb.append('}');
        return sb.toString();
    }

    /**
     * for GSON
     */
    private ThetaState(
            final BigDecimal batteryLevel,
            final URL storageUri,
            final CaptureState captureState,
            final int recordedTime,
            final int recordableTime,
            final int compositeShootingElapsedTime,
            final URL latestFileUrl,
            final BatteryState batteryState,
            final ApiVersion apiVersion,
            final boolean pluginRunning,
            final boolean pluginWebServer,
            final List<String> cameraError
    ) {
        this.batteryLevel = batteryLevel;
        this.storageUri = storageUri;
        this.captureState = captureState;
        this.recordedTime = recordedTime;
        this.recordableTime = recordableTime;
        this.compositeShootingElapsedTime = compositeShootingElapsedTime;
        this.latestFileUrl = latestFileUrl;
        this.batteryState = batteryState;
        this.apiVersion = apiVersion;
        this.pluginRunning = pluginRunning;
        this.pluginWebServer = pluginWebServer;
        this.cameraError = cameraError;
    }
}
