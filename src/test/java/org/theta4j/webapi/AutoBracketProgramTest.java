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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AutoBracketProgramTest {
    private static final Gson GSON = new Gson();

    private static final String JSON;

    private static final AutoBracketProgram PROGRAM;

    static {
        JSON = "{\"_bracketNumber\":3," +
                "\"_bracketParameters\":[" +
                "{\"iso\":100,\"shutterSpeed\":0.001,\"_colorTemperature\":3000}," +
                "{\"iso\":200,\"shutterSpeed\":0.1,\"_colorTemperature\":4000}," +
                "{\"iso\":400,\"shutterSpeed\":1,\"_colorTemperature\":5100}" +
                "]}";

        final AutoBracketProgram.Parameter[] parameters = new AutoBracketProgram.Parameter[]{
                new AutoBracketProgram.Parameter(ISOSpeed._100, ShutterSpeed._1_1000, 3000),
                new AutoBracketProgram.Parameter(ISOSpeed._200, ShutterSpeed._1_10, 4000),
                new AutoBracketProgram.Parameter(ISOSpeed._400, ShutterSpeed._1, 5100),
        };
        PROGRAM = new AutoBracketProgram(parameters);
    }

    @Test
    void testDeserialize() {
        final AutoBracketProgram deserialized = GSON.fromJson(JSON, AutoBracketProgram.class);
        assertEquals(3, deserialized.getNumber());

        final AutoBracketProgram.Parameter p0 = deserialized.getParameters().get(0);
        assertEquals(ISOSpeed._100, p0.getIso());
        assertEquals(ShutterSpeed._1_1000, p0.getShutterSpeed());
        assertEquals(3000, p0.getColorTemperature());

        final AutoBracketProgram.Parameter p1 = deserialized.getParameters().get(1);
        assertEquals(ISOSpeed._200, p1.getIso());
        assertEquals(ShutterSpeed._1_10, p1.getShutterSpeed());
        assertEquals(4000, p1.getColorTemperature());

        final AutoBracketProgram.Parameter p2 = deserialized.getParameters().get(2);
        assertEquals(ISOSpeed._400, p2.getIso());
        assertEquals(ShutterSpeed._1, p2.getShutterSpeed());
        assertEquals(5100, p2.getColorTemperature());
    }

    @Test
    void testSerialize() {
        final String serialized = GSON.toJson(PROGRAM);
        assertEquals(JSON, serialized);
    }

    @Test
    void testNullParameters() {
        assertThrows(NullPointerException.class, () -> new AutoBracketProgram((AutoBracketProgram.Parameter[]) null));
    }

    @Test
    void testParametersContainsNull() {
        final AutoBracketProgram.Parameter[] parameters = new AutoBracketProgram.Parameter[]{
                new AutoBracketProgram.Parameter(ISOSpeed._100, ShutterSpeed._1_1000, 3000),
                null,
                new AutoBracketProgram.Parameter(ISOSpeed._400, ShutterSpeed._1, 5100),
        };

        assertThrows(NullPointerException.class, () -> new AutoBracketProgram(parameters));
    }

    @Nested
    class ParameterTest {
        @Test
        void testNullShutterSpeed() {
            assertThrows(NullPointerException.class, () ->
                    new AutoBracketProgram.Parameter(ISOSpeed._100, null, 5100));
        }
    }
}
