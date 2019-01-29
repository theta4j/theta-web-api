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

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionSetTest {
    private static final Gson GSON = new Gson();

    private static final Option<Integer> ISO = Option.create("iso", Integer.class);
    private static final ArrayOption<Integer> ISO_SUPPORT = ArrayOption.create("isoSupport", Integer.class);
    private static final Option<BigDecimal> APERTURE = Option.create("aperture", BigDecimal.class);

    @Test
    void testDeserialize() {
        final String str = "{\"options\": {\"iso\": 100, \"aperture\": 2.0, \"isoSupport\": [100, 200]}}";
        final OptionSet optionSet = GSON.fromJson(str, OptionSet.class);

        assertEquals(optionSet.get(ISO), Integer.valueOf(100));
        assertEquals(optionSet.get(APERTURE), new BigDecimal("2.0"));
        assertEquals(optionSet.get(ISO_SUPPORT), Arrays.asList(100, 200));
    }

    @Test
    void testSerialize() {
        final String str = "{\"options\": {\"iso\": 100, \"aperture\": 2.0, \"isoSupport\": [100, 200]}}";
        final OptionSet optionSet = GSON.fromJson(str, OptionSet.class);

        assertEquals(optionSet.get(ISO), Integer.valueOf(100));
        assertEquals(optionSet.get(APERTURE), new BigDecimal("2.0"));
        assertEquals(optionSet.get(ISO_SUPPORT), Arrays.asList(100, 200));
    }
}
