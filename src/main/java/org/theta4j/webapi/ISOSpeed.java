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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ISO speed list supported by THETA.
 * Actual supported values are different between each models.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/iso.html">iso · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
@JsonAdapter(ISOSpeed.JsonAdapter.class)
public enum ISOSpeed {
    /**
     * Auto
     */
    AUTO(0),

    /**
     * ISO64
     */
    _64(64),

    /**
     * ISO80
     */
    _80(80),

    /**
     * ISO100
     */
    _100(100),

    /**
     * ISO125
     */
    _125(125),

    /**
     * ISO160
     */
    _160(160),

    /**
     * ISO200
     */
    _200(200),

    /**
     * ISO250
     */
    _250(250),

    /**
     * ISO320
     */
    _320(320),

    /**
     * ISO400
     */
    _400(400),

    /**
     * ISO500
     */
    _500(500),

    /**
     * ISO640
     */
    _640(640),

    /**
     * ISO800
     */
    _800(800),

    /**
     * ISO1000
     */
    _1000(1000),

    /**
     * ISO1250
     */
    _1250(1250),

    /**
     * ISO1600
     */
    _1600(1600),

    /**
     * ISO2000
     */
    _2000(2000),

    /**
     * ISO2500
     */
    _2500(2500),

    /**
     * ISO3200
     */
    _3200(3200),

    /**
     * ISO4000
     */
    _4000(4000),

    /**
     * ISO5000
     */
    _5000(5000),

    /**
     * ISO6400
     */
    _6400(6400);

    private static final Map<Integer, ISOSpeed> map =
            Stream.of(values()).collect(Collectors.toMap(ISOSpeed::getValue, Function.identity()));

    private final int value;

    ISOSpeed(final int value) {
        this.value = value;
    }

    /**
     * Returns the ISO speed value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the string in rational number format.
     * For example {@code "Auto"}, {@code "100"}, {@code "800"}.
     */
    @Override
    @Nonnull
    public String toString() {
        if (this == AUTO) {
            return "Auto";
        }

        return String.valueOf(value);
    }

    static final class JsonAdapter implements JsonDeserializer<ISOSpeed>, JsonSerializer<ISOSpeed> {
        @Override
        public ISOSpeed deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            return map.get(json.getAsInt());
        }

        @Override
        public JsonElement serialize(final ISOSpeed src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(src.value);
        }
    }
}
