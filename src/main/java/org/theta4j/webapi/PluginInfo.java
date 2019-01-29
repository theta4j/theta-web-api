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
import java.util.Objects;
import java.util.Optional;

/**
 * Plug-in information.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._list_plugins.html">camera._listPlugins · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class PluginInfo {
    /**
     * Type of plug-in.
     */
    public enum Type {
        /**
         * Pre-installed plug-in.
         */
        @SerializedName("system") SYSTEM,

        /**
         * User-installed plug-in.
         */
        @SerializedName("user") USER
    }

    private final String pluginName;

    private final String packageName;

    private final String version;

    private final Type type;

    @SerializedName("running")
    private final boolean isRunning;

    @SerializedName("foreground")
    private final boolean inForeground;

    @SerializedName("boot")
    private final boolean isBoot;

    @SerializedName("webServer")
    private final boolean hasWebServer;

    private final String exitStatus;

    private final String message;

    /**
     * Plug-in name.
     */
    @Nonnull
    public String getPluginName() {
        return pluginName;
    }

    /**
     * Package name of plug-in.
     */
    @Nonnull
    public String getPackageName() {
        return packageName;
    }

    /**
     * Plug-in version.
     */
    @Nonnull
    public String getVersion() {
        return version;
    }

    /**
     * Plug-in type.
     */
    @Nonnull
    public Type getType() {
        return type;
    }

    /**
     * Plug-in is running or not.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Plug-in is in foreground or not.
     */
    public boolean isInForeground() {
        return inForeground;
    }

    /**
     * Plug-in is set as boot plug-in or not.
     */
    public boolean isBoot() {
        return isBoot;
    }

    /**
     * Plug-in has web server or not.
     */
    public boolean hasWebServer() {
        return hasWebServer;
    }

    /**
     * Exit status of plug-in.
     */
    public Optional<String> getExitStatus() {
        return Optional.ofNullable(exitStatus);
    }

    /**
     * Exit message of plug-in.
     */
    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PluginInfo that = (PluginInfo) o;
        return isRunning == that.isRunning &&
                inForeground == that.inForeground &&
                isBoot == that.isBoot &&
                hasWebServer == that.hasWebServer &&
                Objects.equals(pluginName, that.pluginName) &&
                Objects.equals(packageName, that.packageName) &&
                Objects.equals(version, that.version) &&
                type == that.type &&
                Objects.equals(exitStatus, that.exitStatus) &&
                Objects.equals(message, that.message);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(pluginName, packageName, version, type, isRunning, inForeground, isBoot, hasWebServer, exitStatus, message);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("PluginInfo{");
        sb.append("pluginName='").append(pluginName).append('\'');
        sb.append(", packageName='").append(packageName).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", type=").append(type);
        sb.append(", isRunning=").append(isRunning);
        sb.append(", inForeground=").append(inForeground);
        sb.append(", isBoot=").append(isBoot);
        sb.append(", hasWebServer=").append(hasWebServer);
        sb.append(", exitStatus='").append(exitStatus).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * for GSON
     */
    private PluginInfo(
            final String pluginName,
            final String packageName,
            final String version,
            final Type type,
            final boolean isRunning,
            final boolean inForeground,
            final boolean isBoot,
            final boolean hasWebServer,
            final String exitStatus,
            final String message
    ) {
        this.pluginName = pluginName;
        this.packageName = packageName;
        this.version = version;
        this.type = type;
        this.isRunning = isRunning;
        this.inForeground = inForeground;
        this.isBoot = isBoot;
        this.hasWebServer = hasWebServer;
        this.exitStatus = exitStatus;
        this.message = message;
    }
}
