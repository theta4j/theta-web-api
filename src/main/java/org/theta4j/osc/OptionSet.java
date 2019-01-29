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

package org.theta4j.osc;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * A set of option and value pairs.
 *
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/camera/getoptions">camera.getOptions | Open Spherical Camera API</a>
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/camera/setoptions">camera.setOptions | Open Spherical Camera API</a>
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/options">Options | Open Spherical Camera API</a>
 */
@JsonAdapter(OptionSet.JsonAdapter.class)
public final class OptionSet {
    private static final Gson GSON = new Gson();

    private final Map<String, JsonElement> map;

    private OptionSet() {
        this(new HashMap<>());
    }

    private OptionSet(Map<String, JsonElement> map) {
        this.map = map;
    }

    /**
     * Get option from this options.
     *
     * @param option Option to get.
     * @param <T>    Type of option value.
     * @return Option value. Returns null if this options does not contain the value of the specified option.
     */
    @Nullable
    public <T> T get(@Nonnull final Option<T> option) {
        Objects.requireNonNull(option, "option can not be null.");

        final JsonElement value = map.get(option.getName());

        if (value == null) {
            return null;
        }

        return GSON.fromJson(value, option.getType());
    }

    /**
     * Get values from this options.
     *
     * @param option Option to get.
     * @param <T>    Type of option value.
     * @return Option value. Returns null if this options does not contain the value of the specified option.
     */
    @Nullable
    public <T> List<T> get(@Nonnull final ArrayOption<T> option) {
        Objects.requireNonNull(option, "option can not be null.");

        final JsonElement value = map.get(option.getName());

        if (value == null) {
            return null;
        }

        return StreamSupport.stream(value.getAsJsonArray().spliterator(), false)
                .map(e -> GSON.fromJson(e, option.getType()))
                .collect(Collectors.toList());
    }

    /**
     * OptionSet builder.
     */
    public static final class Builder {
        private final Map<String, JsonElement> map = new HashMap<>();

        /**
         * Add option.
         *
         * @param option Option to put.
         * @param value  Option value to put.
         * @param <T>    Type of option value.
         * @return Instance of this builder for method chain.
         */
        public <T> Builder put(@Nonnull final Option<T> option, @Nonnull final T value) {
            Objects.requireNonNull(option, "option can not be null.");
            Objects.requireNonNull(value, "value can not be null.");

            map.put(option.getName(), GSON.toJsonTree(value));
            return this;
        }

        /**
         * Create {@link OptionSet} with values which are set to this builder.
         */
        public OptionSet build() {
            return new OptionSet(map);
        }
    }

    static final class JsonAdapter implements JsonDeserializer<OptionSet>, JsonSerializer<OptionSet> {
        @Override
        public OptionSet deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            final OptionSet optionSet = new OptionSet();
            json.getAsJsonObject()
                    .getAsJsonObject("options")
                    .entrySet()
                    .forEach(e -> optionSet.map.put(e.getKey(), e.getValue()));
            return optionSet;
        }

        @Override
        public JsonElement serialize(final OptionSet src, final Type typeOfSrc, final JsonSerializationContext context) {
            final JsonObject optionsJson = new JsonObject();
            src.map.forEach(optionsJson::add);

            final JsonObject json = new JsonObject();
            json.add("options", optionsJson);

            return json;
        }
    }
}
