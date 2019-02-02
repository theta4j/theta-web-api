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

import com.burgstaller.okhttp.AuthenticationCacheInterceptor;
import com.burgstaller.okhttp.CachingAuthenticatorDecorator;
import com.burgstaller.okhttp.digest.CachingAuthenticator;
import com.burgstaller.okhttp.digest.Credentials;
import com.burgstaller.okhttp.digest.DigestAuthenticator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

/**
 * OSCClient is a client of Open Spherical Camera API.
 *
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/">Open Spherical Camera API</a>
 */
public final class OSCClient {
    private static final Gson GSON = new Gson();

    private static final Command<JsonObject, OptionSet> GET_OPTIONS_COMMAND = Command.create("camera.getOptions", JsonObject.class, OptionSet.class);

    private static final Command<OptionSet, Void> SET_OPTIONS_COMMAND = Command.create("camera.setOptions", OptionSet.class, Void.class);

    private final String endpoint;

    private final OkHttpClient httpClient;

    private OSCClient(final String endpoint, final OkHttpClient httpClient) {
        this.endpoint = endpoint;
        this.httpClient = httpClient;
    }

    /**
     * Create OSCClient.
     *
     * @param endpoint Endpoint of the OSC API server. For example "http://192.168.1.1".
     * @throws NullPointerException if endpoint is null.
     */
    public static OSCClient create(@Nonnull final String endpoint) {
        Objects.requireNonNull(endpoint, "endpoint can not be null.");

        return new OSCClient(endpoint, new OkHttpClient());
    }

    /**
     * Create OSCClient with Digest authentication.
     *
     * @param endpoint Endpoint of the OSC API server. For example "http://192.168.1.1".
     * @param username Username for Digest authentication.
     * @param password Password for Digest authentication.
     * @throws NullPointerException if endpoint, username or password is null.
     */
    public static OSCClient createWithDigestAuthentication(@Nonnull final String endpoint, @Nonnull final String username, @Nonnull final String password) {
        Objects.requireNonNull(endpoint, "endpoint can not be null.");
        Objects.requireNonNull(username, "username can not be null.");
        Objects.requireNonNull(password, "password can not be null.");

        final com.burgstaller.okhttp.digest.Credentials credentials = new Credentials(username, password);
        final DigestAuthenticator authenticator = new DigestAuthenticator(credentials);
        final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();

        final OkHttpClient httpClient = new OkHttpClient.Builder()
                .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
                .addInterceptor(new AuthenticationCacheInterceptor(authCache))
                .build();

        return new OSCClient(endpoint, httpClient);
    }

    /**
     * GET /osc/info
     *
     * @param resultType a type of the result value.
     * @return Information object
     * @throws IOException          I/O error is occurred.
     * @throws NullPointerException if resultType is null.
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/info">Info | Open Spherical Camera API</a>
     */
    @Nonnull
    public <T> T info(@Nonnull final Class<T> resultType) throws IOException {
        Objects.requireNonNull(resultType, "resultType can not be null.");

        final JsonObject response = httpGet(endpoint + "/osc/info");
        return GSON.fromJson(response, resultType);
    }

    /**
     * POST /osc/state
     *
     * @param resultType a type of the state value.
     * @throws IOException          I/O error is occurred.
     * @throws NullPointerException if resultType is null.
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/state">State | Open Spherical Camera API</a>
     */
    @Nonnull
    public <T> OSCState<T> state(@Nonnull final Class<T> resultType) throws IOException {
        Objects.requireNonNull(resultType, "resultType can not be null.");

        final JsonObject response = httpPost(endpoint + "/osc/state", null);
        return OSCState.valueOf(response, resultType);
    }

    /**
     * Check for the updates of the state.
     *
     * @param fingerprint fingerprint of the last state.
     * @return updated fingerprint.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if fingerprint is null.
     * @see OSCClient#state(Class)
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/checkforupdates">CheckForUpdates | Open Spherical Camera API</a>
     */
    @Nonnull
    public String checkForUpdates(@Nonnull final String fingerprint) throws IOException {
        Objects.requireNonNull(fingerprint, "fingerprint can not be null.");

        final JsonObject request = new JsonObject();
        request.addProperty("stateFingerprint", fingerprint);
        final JsonObject response = httpPost(endpoint + "/osc/checkForUpdates", request);

        if (Objects.equals(response.get("state").getAsString(), "error")) {
            throw GSON.fromJson(response, OSCException.class);
        }

        return response.get("stateFingerprint").getAsString();
    }

    /**
     * Execute command.
     *
     * @param command the command to execute.
     * @param <P>     a type of the parameter.
     * @param <R>     a type of the result.
     * @return The response of the command.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if command is null.
     */
    @Nonnull
    public <P, R> CommandResponse<R> commandExecute(@Nonnull final Command<P, R> command) throws IOException {
        return commandExecute(command, null);
    }

    /**
     * Execute command.
     *
     * @param command   the command to execute.
     * @param parameter the parameter of the command.
     * @param <P>       a type of the parameter.
     * @param <R>       a type of the result.
     * @return The response of the command.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if command is null.
     */
    @Nonnull
    public <P, R> CommandResponse<R> commandExecute(@Nonnull final Command<P, R> command, @Nullable final P parameter) throws IOException {
        Objects.requireNonNull(command, "command can not be null.");

        final CommandRequest reqBody = new CommandRequest(command.getName(), parameter);
        final JsonObject resBody = httpPost(endpoint + "/osc/commands/execute", GSON.toJsonTree(reqBody).getAsJsonObject());

        final CommandResponse<R> response = CommandResponse.valueOf(resBody, command.getResultType());

        if (response.getError() != null) {
            throw response.getError();
        }

        return response;
    }

    /**
     * Check status of the executed command.
     *
     * @param response The response value of last
     * @return Updated command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if response is null or response does not have ID.
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/commands/status">Status | Open Spherical Camera API</a>
     */
    @Nonnull
    public <R> CommandResponse<R> commandStatus(@Nonnull final CommandResponse<R> response) throws IOException {
        Objects.requireNonNull(response, "response can not be null.");

        // request and get response
        final JsonObject reqBody = new JsonObject();
        reqBody.addProperty("id", response.getID());
        final JsonObject resBody = httpPost(endpoint + "/osc/commands/status", reqBody);

        final CommandResponse<R> updatedResponse = CommandResponse.valueOf(resBody, response.getResultType());

        if (updatedResponse.getError() != null) {
            throw updatedResponse.getError();
        }

        return updatedResponse;
    }

    /**
     * Get option value.
     *
     * @param option Option to acquire value.
     * @param <T>    Type of option value.
     * @return Acquired option value.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if option is null.
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/camera/getoptions">camera.getOptions | Open Spherical Camera API</a>
     */
    @Nonnull
    public <T> T getOption(@Nonnull final Option<T> option) throws IOException {
        Objects.requireNonNull(option, "options can not be null.");
        return getOptions(option).get(option);
    }

    /**
     * Get option value.
     *
     * @param option Option to acquire value.
     * @param <T>    Type of option value.
     * @return Acquired option values.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if option is null.
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/camera/getoptions">camera.getOptions | Open Spherical Camera API</a>
     */
    @Nonnull
    public <T> List<T> getOption(@Nonnull final ArrayOption<T> option) throws IOException {
        Objects.requireNonNull(option, "options can not be null.");
        return getOptions(option).get(option);
    }

    /**
     * Get options.
     *
     * @param options options to acquire.
     * @return Acquired options values.
     * @throws IOException              I/O error is occurred.
     * @throws OSCException             Server returned error response.
     * @throws NullPointerException     if options is null or contains null.
     * @throws IllegalArgumentException if length of options is 0.
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/camera/getoptions">camera.getOptions | Open Spherical Camera API</a>
     */
    @Nonnull
    public OptionSet getOptions(@Nonnull final Option... options) throws IOException {
        return getOptions(Arrays.asList(options));
    }

    /**
     * Get options.
     *
     * @param options options to acquire.
     * @return Acquired options values.
     * @throws IOException              I/O error is occurred.
     * @throws OSCException             Server returned error response.
     * @throws NullPointerException     if options is null or contains null.
     * @throws IllegalArgumentException if length of options is 0.
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/camera/getoptions">camera.getOptions | Open Spherical Camera API</a>
     */
    @Nonnull
    public OptionSet getOptions(@Nonnull final Collection<Option> options) throws IOException {
        Objects.requireNonNull(options, "options can not be null.");
        if (options.size() == 0) {
            throw new IllegalArgumentException("options must have 1 or more entries.");
        }
        if (options.contains(null)) {
            throw new NullPointerException("names can not contain null.");
        }

        final JsonArray optionNames = new JsonArray(options.size());
        options.stream()
                .map(Option::getName)
                .forEach(optionNames::add);

        final JsonObject parameter = new JsonObject();
        parameter.add("optionNames", optionNames);

        final CommandResponse<OptionSet> response = commandExecute(GET_OPTIONS_COMMAND, parameter);

        if (response.getError() != null) {
            throw response.getError();
        }

        return response.getResult();
    }

    /**
     * Set options value.
     *
     * @param option Option to set value.
     * @param value  Option value to set.
     * @param <T>    Type of option value.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if option or value is null.
     */
    @Nonnull
    public <T> void setOption(@Nonnull final Option<T> option, T value) throws IOException {
        final OptionSet optionSet = new OptionSet.Builder()
                .put(option, value)
                .build();
        setOptions(optionSet);
    }

    /**
     * Set optionSet.
     *
     * @param optionSet OptionSet values to set.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if optionSet is null.
     * @see <a href="https://developers.google.com/streetview/open-spherical-camera/reference/camera/setoptions">camera.setOptions | Open Spherical Camera API</a>
     */
    public void setOptions(@Nonnull final OptionSet optionSet) throws IOException {
        Objects.requireNonNull(optionSet, "optionSet can not be null.");

        final CommandResponse<Void> response = commandExecute(SET_OPTIONS_COMMAND, optionSet);

        if (response.getError() != null) {
            throw response.getError();
        }
    }

    // Helpers

    private JsonObject httpGet(final String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("X-XSRF-Protected", "1")
                .build();

        final Response response = httpClient.newCall(request).execute();

        if (response.code() == HTTP_UNAUTHORIZED) {
            throw new IOException(response.message());
        }

        try (final Reader r = response.body().charStream()) {
            return GSON.fromJson(r, JsonObject.class);
        }
    }

    private JsonObject httpPost(final String url, final JsonObject body) throws IOException {
        final RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=UTF-8"),
                body != null ? GSON.toJson(body) : "");

        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Accept", "application/json")
                .addHeader("X-XSRF-Protected", "1")
                .build();

        final Response response = httpClient.newCall(request).execute();

        if (response.code() == HTTP_UNAUTHORIZED) {
            throw new IOException(response.message());
        }

        try (final Reader r = response.body().charStream()) {
            return GSON.fromJson(r, JsonObject.class);
        }
    }
}
