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

/**
 * Support value of captureNumber option.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/capture_number.html">captureNumber · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class CaptureNumberSupport {
    @SerializedName("_limitless")
    private final int limitless;

    private final int minNumber;

    private final int maxNumber;

    private CaptureNumberSupport(final int limitless, final int minNumber, final int maxNumber) {
        this.limitless = limitless;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    /**
     * Returns magic number meaning "limitless".
     */
    public int getLimitless() {
        return limitless;
    }

    /**
     * Minimum number.
     */
    public int getMinNumber() {
        return minNumber;
    }

    /**
     * Maximum number.
     */
    public int getMaxNumber() {
        return maxNumber;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaptureNumberSupport that = (CaptureNumberSupport) o;
        return limitless == that.limitless &&
                minNumber == that.minNumber &&
                maxNumber == that.maxNumber;
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(limitless, minNumber, maxNumber);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("CaptureNumberSupport{");
        sb.append("limitless=").append(limitless);
        sb.append(", minNumber=").append(minNumber);
        sb.append(", maxNumber=").append(maxNumber);
        sb.append('}');
        return sb.toString();
    }
}
