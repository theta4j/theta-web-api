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
 * Support value of _colorTemperature option.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_color_temperature.html">_colorTemperature · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class ColorTemperatureSupport {
    private final int minTemperature;

    private final int maxTemperature;

    private final int stepSize;

    private ColorTemperatureSupport(final int minTemperature, final int maxTemperature, final int stepSize) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.stepSize = stepSize;
    }

    /**
     * Minimum value.
     */
    public int getMinTemperature() {
        return minTemperature;
    }

    /**
     * Maximum value.
     */
    public int getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * Step size.
     */
    public int getStepSize() {
        return stepSize;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorTemperatureSupport that = (ColorTemperatureSupport) o;
        return minTemperature == that.minTemperature &&
                maxTemperature == that.maxTemperature &&
                stepSize == that.stepSize;
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(minTemperature, maxTemperature, stepSize);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColorTemperatureSupport{");
        sb.append("minTemperature=").append(minTemperature);
        sb.append(", maxTemperature=").append(maxTemperature);
        sb.append(", stepSize=").append(stepSize);
        sb.append('}');
        return sb.toString();
    }
}
