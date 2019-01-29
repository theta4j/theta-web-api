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

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;

import javax.annotation.Nonnull;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Exposure compensation (EV) values supported by THETA.
 * Actual supported values are different between each models.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/exposure_compensation.html">exposureCompensation · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
@JsonAdapter(ExposureCompensation.JsonAdapter.class)
public enum ExposureCompensation {
    /**
     * -2.0EV
     */
    MINUS_2_0("-2.0"),

    /**
     * -1.7EV
     */
    MINUS_1_7("-1.7"),

    /**
     * -1.3EV
     */
    MINUS_1_3("-1.3"),

    /**
     * -1.0EV
     */
    MINUS_1_0("-1.0"),

    /**
     * -0.7EV
     */
    MINUS_0_7("-0.7"),

    /**
     * -0.3EV
     */
    MINUS_0_3("-0.3"),

    /**
     * 0.0EV
     */
    _0_0("0.0"),

    /**
     * +0.3EV
     */
    PLUS_0_3("0.3"),

    /**
     * +0.7EV
     */
    PLUS_0_7("0.7"),

    /**
     * +1.0EV
     */
    PLUS_1_0("1.0"),

    /**
     * +1.3EV
     */
    PLUS_1_3("1.3"),

    /**
     * +1.7EV
     */
    PLUS_1_7("1.7"),

    /**
     * +2.0EV
     */
    PLUS_2_0("2.0");

    private final BigDecimal value;

    private static final Map<BigDecimal, ExposureCompensation> map =
            Stream.of(values()).collect(Collectors.toMap(ExposureCompensation::getValue, Function.identity()));

    ExposureCompensation(final String value) {
        this.value = new BigDecimal(value);
    }

    /**
     * Returns the exposure compensation value.
     */
    @Nonnull
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Returns the string. For example {@code "-2.0"}, {@code "0.0EV"}, {@code "+0.3EV"}.
     */
    @Override
    @Nonnull
    public String toString() {
        return (value.signum() > 0 ? "+" : "") + value.toPlainString() + "EV";
    }

    static final class JsonAdapter implements JsonDeserializer<ExposureCompensation>, JsonSerializer<ExposureCompensation> {
        @Override
        public ExposureCompensation deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            return map.get(json.getAsBigDecimal());
        }

        @Override
        public JsonElement serialize(final ExposureCompensation src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(src.value);
        }
    }
}
