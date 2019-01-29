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
 * Shutter speed list supported by THETA.
 * Actual supported values are different between each models.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/shutter_speed.html">shutterSpeed · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
@JsonAdapter(ShutterSpeed.JsonAdapter.class)
public enum ShutterSpeed {
    /**
     * Auto
     */
    AUTO("0", "0", "1"),

    /**
     * 1/25000s
     */
    _1_25000("0.00004", "1", "25000"),

    /**
     * 1/20000s
     */
    _1_20000("0.00005", "1", "20000"),

    /**
     * 1/16000s
     */
    _1_16000("0.0000625", "1", "16000"),

    /**
     * 1/12500s
     */
    _1_12500("0.00008", "1", "12500"),

    /**
     * 1/10000s
     */
    _1_10000("0.0001", "1", "10000"),

    /**
     * 1/8000s
     */
    _1_8000("0.000125", "1", "8000"),

    /**
     * 1/6400s
     */
    _1_6400("0.00015625", "1", "6400"),

    /**
     * 1/5000s
     */
    _1_5000("0.0002", "1", "5000"),

    /**
     * 1/4000s
     */
    _1_4000("0.00025", "1", "4000"),

    /**
     * 1/3200s
     */
    _1_3200("0.0003125", "1", "3200"),

    /**
     * 1/2500s
     */
    _1_2500("0.0004", "1", "2500"),

    /**
     * 1/2000s
     */
    _1_2000("0.0005", "1", "2000"),

    /**
     * 1/1600s
     */
    _1_1600("0.000625", "1", "1600"),

    /**
     * 1/1250s
     */
    _1_1250("0.0008", "1", "1250"),

    /**
     * 1/1000s
     */
    _1_1000("0.001", "1", "1000"),

    /**
     * 1/800s
     */
    _1_800("0.00125", "1", "800"),

    /**
     * 1/640s
     */
    _1_640("0.0015625", "1", "640"),

    /**
     * 1/500s
     */
    _1_500("0.002", "1", "500"),

    /**
     * 1/400s
     */
    _1_400("0.0025", "1", "400"),

    /**
     * 1/320s
     */
    _1_320("0.003125", "1", "320"),

    /**
     * 1/250s
     */
    _1_250("0.004", "1", "250"),

    /**
     * 1/200s
     */
    _1_200("0.005", "1", "200"),

    /**
     * 1/160s
     */
    _1_160("0.00625", "1", "160"),

    /**
     * 1/125s
     */
    _1_125("0.008", "1", "125"),

    /**
     * 1/100s
     */
    _1_100("0.01", "1", "100"),

    /**
     * 1/80s
     */
    _1_80("0.0125", "1", "80"),

    /**
     * 1/60s
     */
    _1_60("0.01666666", "1", "60"),

    /**
     * 1/50s
     */
    _1_50("0.02", "1", "50"),

    /**
     * 1/40s
     */
    _1_40("0.025", "1", "40"),

    /**
     * 1/30s
     */
    _1_30("0.03333333", "1", "30"),

    /**
     * 1/25s
     */
    _1_25("0.04", "1", "25"),

    /**
     * 1/20s
     */
    _1_20("0.05", "1", "20"),

    /**
     * 1/15s
     */
    _1_15("0.06666666", "1", "15"),

    /**
     * 1/13s
     */
    _1_13("0.07692307", "1", "13"),

    /**
     * 1/10s
     */
    _1_10("0.1", "1", "10"),

    /**
     * 1/8s
     */
    _1_8("0.125", "1", "8"),

    /**
     * 1/6s
     */
    _1_6("0.16666666", "1", "6"),

    /**
     * 1/5s
     */
    _1_5("0.2", "1", "5"),

    /**
     * 1/4s
     */
    _1_4("0.25", "1", "4"),

    /**
     * 1/3s
     */
    _1_3("0.33333333", "1", "3"),

    /**
     * 1/2.5s
     */
    _1_2P5("0.4", "1", "2.5"),

    /**
     * 1/2s
     */
    _1_2("0.5", "1", "2"),

    /**
     * 1/1.6s
     */
    _1_1P6("0.625", "1", "1.6"),

    /**
     * 1/1.3s
     */
    _1_1P3("0.76923076", "1", "1.3"),

    /**
     * 1s
     */
    _1("1", "1", "1"),

    /**
     * 1.3s
     */
    _1P3("1.3", "1.3", "1"),

    /**
     * 1.6s
     */
    _1P6("1.6", "1.6", "1"),

    /**
     * 2s
     */
    _2("2", "2", "1"),

    /**
     * 2.5s
     */
    _2P5("2.5", "2.5", "1"),

    /**
     * 3.2s
     */
    _3P2("3.2", "3.2", "1"),

    /**
     * 4s
     */
    _4("4", "4", "1"),

    /**
     * 5s
     */
    _5("5", "5", "1"),

    /**
     * 6s
     */
    _6("6", "6", "1"),

    /**
     * 8s
     */
    _8("8", "8", "1"),

    /**
     * 10s
     */
    _10("10", "10", "1"),

    /**
     * 13s
     */
    _13("13", "13", "1"),

    /**
     * 15s
     */
    _15("15", "15", "1"),

    /**
     * 20s
     */
    _20("20", "20", "1"),

    /**
     * 25s
     */
    _25("25", "25", "1"),

    /**
     * 30s
     */
    _30("30", "30", "1"),

    /**
     * 60s
     */
    _60("60", "60", "1");

    private static final Map<BigDecimal, ShutterSpeed> map =
            Stream.of(values()).collect(Collectors.toMap(ShutterSpeed::getValue, Function.identity()));

    private final BigDecimal value;

    private final BigDecimal numerator, denominator;

    ShutterSpeed(final String value, final String numerator, final String denominator) {
        this.value = new BigDecimal(value);
        this.numerator = new BigDecimal(numerator);
        this.denominator = new BigDecimal(denominator);
    }

    /**
     * Returns the shutter speed in sec.
     */
    @Nonnull
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Returns the numerator of rational number format.
     */
    @Nonnull
    public BigDecimal getNumerator() {
        return numerator;
    }

    /**
     * Returns the denominator of rational number format.
     */
    @Nonnull
    public BigDecimal getDenominator() {
        return denominator;
    }

    /**
     * Returns the string in rational number format.
     * For example {@code "Auto"}, {@code "1/25000s"}, {@code "1/10s"}, {@code "1s"}, and {@code "15s"}.
     */
    @Override
    @Nonnull
    public String toString() {
        if (this == AUTO) {
            return "Auto";
        }

        return numerator.toPlainString() + "/" + denominator.toPlainString() + "s";
    }

    static final class JsonAdapter implements JsonDeserializer<ShutterSpeed>, JsonSerializer<ShutterSpeed> {
        @Override
        public ShutterSpeed deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            return map.get(json.getAsBigDecimal());
        }

        @Override
        public JsonElement serialize(final ShutterSpeed src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(src.value);
        }
    }
}
