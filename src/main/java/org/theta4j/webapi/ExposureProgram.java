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

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Exposure program mode supported by THETA.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/exposure_program.html">exposureProgram · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
@JsonAdapter(ExposureProgram.JsonAdapter.class)
public enum ExposureProgram {
    /**
     * Manual program. Manually set the {@link ISOSpeed} and the {@link ShutterSpeed}.
     */
    MANUAL(1),

    /**
     * Normal program. Exposure settings are all set automatically.
     */
    AUTO(2),

    /**
     * Aperture priority program. Manually set the {@link Aperture}.
     */
    APERTURE(3),

    /**
     * Shutter speed priority program. Manually set the {@link ShutterSpeed}.
     */
    SHUTTER_SPEED(4),

    /**
     * ISO speed priority program. Manually set the {@link ISOSpeed}.
     */
    ISO_SPEED(9);

    private static final Map<Integer, ExposureProgram> map =
            Stream.of(values()).collect(Collectors.toMap(ExposureProgram::getValue, Function.identity()));

    private final int value;

    ExposureProgram(final int value) {
        this.value = value;
    }

    private int getValue() {
        return value;
    }

    static final class JsonAdapter implements JsonDeserializer<ExposureProgram>, JsonSerializer<ExposureProgram> {
        @Override
        public ExposureProgram deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            return map.get(json.getAsInt());
        }

        @Override
        public JsonElement serialize(final ExposureProgram src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(src.value);
        }
    }
}
