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
 * Support value of bracketNumber in _autoBracket option.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_auto_bracket.html">_autoBracket · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class BracketNumberSupport {
    private final int minNumber;

    private final int maxNumber;

    private final int stepSize;

    private BracketNumberSupport(final int minNumber, final int maxNumber, final int stepSize) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.stepSize = stepSize;
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
     * Step size.
     *
     * @return
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
        BracketNumberSupport that = (BracketNumberSupport) o;
        return minNumber == that.minNumber &&
                maxNumber == that.maxNumber &&
                stepSize == that.stepSize;
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(minNumber, maxNumber, stepSize);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("BracketNumberSupport{");
        sb.append("minNumber=").append(minNumber);
        sb.append(", maxNumber=").append(maxNumber);
        sb.append(", stepSize=").append(stepSize);
        sb.append('}');
        return sb.toString();
    }
}
