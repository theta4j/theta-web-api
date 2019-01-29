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
 * Wireless LAN channel.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_wlan_channel.html">_wlanChannel · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
@JsonAdapter(WlanChannel.JsonAdapter.class)
public enum WlanChannel {
    NOT_SPECIFIED(0),
    _1(1),
    _6(6),
    _11(11);

    private static final Map<Integer, WlanChannel> map =
            Stream.of(values()).collect(Collectors.toMap(WlanChannel::getValue, Function.identity()));

    private final int value;

    WlanChannel(final int value) {
        this.value = value;
    }

    /**
     * Returns the channel number.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the string. For example {@code "Not Specified"}, {@code "1"}, {@code "6"}.
     */
    @Override
    @Nonnull
    public String toString() {
        if (value == 0) {
            return "Not Specified";
        }
        return String.valueOf(value);
    }

    static final class JsonAdapter implements JsonDeserializer<WlanChannel>, JsonSerializer<WlanChannel> {
        @Override
        public WlanChannel deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            return map.get(json.getAsInt());
        }

        @Override
        public JsonElement serialize(final WlanChannel src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(src.value);
        }
    }
}
