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

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiVersionTest {
    private static final Gson GSON = new Gson();

    @Test
    void testDeserialize() {
        assertEquals(ApiVersion.V2_0, GSON.fromJson("1", ApiVersion.class));
        assertEquals(ApiVersion.V2_1, GSON.fromJson("2", ApiVersion.class));
    }

    @Test
    void testSerialize() {
        assertEquals("1", GSON.toJson(ApiVersion.V2_0));
        assertEquals("2", GSON.toJson(ApiVersion.V2_1));
    }
}
