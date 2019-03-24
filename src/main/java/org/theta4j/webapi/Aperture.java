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
 * Apertures (F-numbers) supported by THETA.
 * Actual supported values are different between each models.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/aperture.html">aperture · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
@JsonAdapter(Aperture.JsonAdapter.class)
public enum Aperture {
    /**
     * F2.0
     */
    F2_0("2.0"),

    /**
     * F2.1
     */
    F2_1("2.1"),

    /**
     * F3.5
     */
    F3_5("3.5"),

    /**
     * F5.6
     */
    F5_6("5.6");

    private static final Map<BigDecimal, Aperture> map =
            Stream.of(values()).collect(Collectors.toMap(Aperture::getValue, Function.identity()));

    private final BigDecimal value;

    Aperture(String value) {
        this.value = new BigDecimal(value);
    }

    /**
     * Returns the aperture (F-number).
     */
    @Nonnull
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Returns the string. For example {@code "F2.0"}.
     */
    @Override
    @Nonnull
    public String toString() {
        return "F" + value.toPlainString();
    }

    static final class JsonAdapter implements JsonDeserializer<Aperture>, JsonSerializer<Aperture> {
        @Override
        public Aperture deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            return map.get(json.getAsBigDecimal());
        }

        @Override
        public JsonElement serialize(final Aperture src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(src.value);
        }
    }
}
