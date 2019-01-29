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

import java.lang.reflect.Type;

/**
 * GsonUtils is utilities for GSON
 */
public final class GsonUtils {
    /**
     * Replace empty string with null on deserializing time.
     *
     * @param <T> Type of value.
     */
    public static final class EmptyStringAsNullAdapter<T> implements JsonDeserializer<T> {
        @Override
        public T deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonPrimitive()) {
                final JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
                if (jsonPrimitive.isString() && jsonPrimitive.getAsString().isEmpty()) {
                    return null;
                }
            }
            return context.deserialize(json, typeOfT);
        }
    }

    private GsonUtils() {
        throw new AssertionError();
    }
}
