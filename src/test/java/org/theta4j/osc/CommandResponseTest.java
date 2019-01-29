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

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.theta4j.webapi.TakePicture;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class CommandResponseTest {
    @Test
    void testValueOf() throws Exception {
        final JsonObject resultsJson = new JsonObject();
        resultsJson.addProperty("fileUrl", "http://example.com/foo");

        final JsonObject responseJson = new JsonObject();
        responseJson.addProperty("name", "camera.takePicture");
        responseJson.addProperty("state", "done");
        responseJson.addProperty("id", "foo");
        responseJson.add("results", resultsJson);

        final CommandResponse<TakePicture.Result> response =
                CommandResponse.valueOf(responseJson, TakePicture.Result.class);

        assertEquals(response.getName(), "camera.takePicture");
        assertEquals(response.getState(), CommandState.DONE);
        assertEquals(response.getID(), "foo");
        assertNotNull(response.getResult());
        assertEquals(response.getResult().getFileUrl(), new URL("http://example.com/foo"));
        assertNull(response.getError());
        assertNull(response.getProgress());
    }
}
