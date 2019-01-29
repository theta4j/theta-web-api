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
import com.google.gson.JsonObject;

import javax.annotation.Nonnull;

/**
 * OSCState represents result of POST /osc/state
 *
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/state">State | Open Spherical Camera API</a>
 */
public final class OSCState<T> {
    private static final Gson GSON = new Gson();

    private final String fingerprint;

    private final T state;

    private OSCState(final String fingerprint, T state) {
        this.fingerprint = fingerprint;
        this.state = state;
    }

    static <T> OSCState<T> valueOf(final JsonObject json, final Class<T> resultType) {
        final String fingerprint = json.get("fingerprint").getAsString();
        final T state = GSON.fromJson(json.getAsJsonObject("state"), resultType);
        return new OSCState<>(fingerprint, state);
    }

    /**
     * Fingerprint (unique identifier) of the current camera state.
     */
    @Nonnull
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * Status of various the camera states.
     */
    @Nonnull
    public T getState() {
        return state;
    }
}
