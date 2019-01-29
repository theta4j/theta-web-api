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
 * Support value of captureInterval option.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/capture_interval.html">captureInterval · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class CaptureIntervalSupport {
    private final int minInterval;

    private final int maxInterval;

    private CaptureIntervalSupport(final int minInterval, final int maxInterval) {
        this.minInterval = minInterval;
        this.maxInterval = maxInterval;
    }

    /**
     * Minimum value.
     */
    public int getMinInterval() {
        return minInterval;
    }

    /**
     * Maximum value.
     */
    public int getMaxInterval() {
        return maxInterval;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaptureIntervalSupport that = (CaptureIntervalSupport) o;
        return minInterval == that.minInterval &&
                maxInterval == that.maxInterval;
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(minInterval, maxInterval);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("CaptureIntervalSupport{");
        sb.append("minInterval=").append(minInterval);
        sb.append(", maxInterval=").append(maxInterval);
        sb.append('}');
        return sb.toString();
    }
}
