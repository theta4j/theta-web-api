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
 * Support value of bracketNumber in _shutterVolume option.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_shutter_volume.html">_shutterVolume · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class ShutterVolumeSupport {
    private final int minShutterVolume;

    private final int maxShutterVolume;

    private ShutterVolumeSupport(final int minShutterVolume, final int maxShutterVolume) {
        this.minShutterVolume = minShutterVolume;
        this.maxShutterVolume = maxShutterVolume;
    }

    public int getMinShutterVolume() {
        return minShutterVolume;
    }

    public int getMaxShutterVolume() {
        return maxShutterVolume;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShutterVolumeSupport that = (ShutterVolumeSupport) o;
        return minShutterVolume == that.minShutterVolume &&
                maxShutterVolume == that.maxShutterVolume;
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(minShutterVolume, maxShutterVolume);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShutterVolumeSupport{");
        sb.append("minShutterVolume=").append(minShutterVolume);
        sb.append(", maxShutterVolume=").append(maxShutterVolume);
        sb.append('}');
        return sb.toString();
    }
}
