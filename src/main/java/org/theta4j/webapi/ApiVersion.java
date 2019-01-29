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
 * API version of the camera.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/client_version.html">clientVersion · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
@JsonAdapter(ApiVersion.JsonAdapter.class)
public enum ApiVersion {
    /**
     * RICOH THETA API v2.0
     */
    V2_0(1, "v2.0"),

    /**
     * RICOH THETA API v2.1
     */
    V2_1(2, "v2.1");

    private static final Map<Integer, ApiVersion> map =
            Stream.of(values()).collect(Collectors.toMap(v -> v.value, Function.identity()));

    private final int value;

    private final String name;

    ApiVersion(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Returns the string. For example {@code "v2.0"}, {@code "v2.1"}.
     */
    @Override
    @Nonnull
    public String toString() {
        return name;
    }

    static final class JsonAdapter implements JsonDeserializer<ApiVersion>, JsonSerializer<ApiVersion> {
        @Override
        public ApiVersion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return map.get(json.getAsInt());
        }

        @Override
        public JsonElement serialize(ApiVersion src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.value);
        }
    }
}
