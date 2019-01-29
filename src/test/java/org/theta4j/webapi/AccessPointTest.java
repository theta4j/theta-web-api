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
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccessPointTest {
    private static final Gson GSON = new Gson();

    private static final String JSON_FULL, JSON_MIN;

    private static final AccessPoint ACCESS_POINT_FULL, ACCESS_POINT_MIN;

    static {
        // with full values
        final JsonObject jsonFull = new JsonObject();
        jsonFull.addProperty("ssid", "full_ssid");
        jsonFull.addProperty("ssidStealth", true);
        jsonFull.addProperty("security", "WPA/WPA2 PSK");
        jsonFull.addProperty("password", "full_password");
        jsonFull.addProperty("connectionPriority", 5);
        jsonFull.addProperty("ipAddressAllocation", "static");
        jsonFull.addProperty("ipAddress", "192.168.1.46");
        jsonFull.addProperty("subnetMask", "255.255.255.0");
        jsonFull.addProperty("defaultGateway", "192.168.1.1");
        JSON_FULL = GSON.toJson(jsonFull);
        ACCESS_POINT_FULL = new AccessPoint.Builder("full_ssid")
                .security(AccessPoint.Security.WPA_WPA2_PSK, "full_password")
                .isStealth(true)
                .connectionPriority(5)
                .ipAddress("192.168.1.46", "255.255.255.0", "192.168.1.1")
                .build();

        // with minimum values
        final JsonObject jsonMin = new JsonObject();
        jsonMin.addProperty("ssid", "min_ssid");
        jsonMin.addProperty("security", "none");
        JSON_MIN = GSON.toJson(jsonMin);
        ACCESS_POINT_MIN = new AccessPoint.Builder("min_ssid").build();
    }

    @Test
    void testDeserializeFull() {
        final AccessPoint deserialized = GSON.fromJson(JSON_FULL, AccessPoint.class);
        assertEquals(ACCESS_POINT_FULL, deserialized);
    }

    @Test
    void testSerializeFull() {
        final String serialized = GSON.toJson(ACCESS_POINT_FULL);
        assertEquals(JSON_FULL, serialized);
    }

    @Test
    void testDeserializeMin() {
        final AccessPoint deserialized = GSON.fromJson(JSON_MIN, AccessPoint.class);
        assertEquals(ACCESS_POINT_MIN, deserialized);
    }

    @Test
    void testSerializeMin() {
        final String serialized = GSON.toJson(ACCESS_POINT_MIN);
        assertEquals(JSON_MIN, serialized);
    }
}
