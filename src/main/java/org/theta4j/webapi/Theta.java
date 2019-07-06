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

import org.theta4j.osc.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * RICOH THETA Web API v2.1 client.
 */
public final class Theta {
    private static final String DEFAULT_ENDPOINT = "http://192.168.1.1";

    private static final String INTERNAL_ENDPOINT = "http:127.0.0.1:8080";

    private final OSCClient oscClient;

    private Theta(final OSCClient oscClient) {
        this.oscClient = oscClient;
    }

    /**
     * Create new {@link Theta}. This is for THETA in access point mode.
     *
     * @return Created instance.
     */
    @Nonnull
    public static Theta create() {
        return create(DEFAULT_ENDPOINT);
    }

    /**
     * Create new {@link Theta}. This is for THETA in client mode.
     *
     * @param endpoint Endpoint URL. For example, "http://192.168.4.110"
     * @return Created instance.
     * @throws NullPointerException if endpoint is null.
     */
    @Nonnull
    public static Theta create(@Nonnull final String endpoint) {
        Objects.requireNonNull(endpoint, "endpoint can not be null.");

        return new Theta(OSCClient.create(endpoint));
    }

    /**
     * Create new {@link Theta}. This is for THETA in client mode with Digest Authentication.
     *
     * @param endpoint Endpoint URL. For example, "http://192.168.4.110"
     * @param username Username for digest authentication.
     * @param password Password for digest authentication.
     * @return Created instance.
     * @throws NullPointerException if endpoint, username, or password is null.
     */
    @Nonnull
    public static Theta create(@Nonnull final String endpoint, @Nonnull final String username, @Nonnull final String password) {
        Objects.requireNonNull(endpoint, "endpoint can not be null.");
        Objects.requireNonNull(username, "username can not be null.");
        Objects.requireNonNull(password, "password can not be null.");

        final OSCClient oscClient = OSCClient.createWithDigestAuthentication(endpoint, username, password);
        return new Theta(oscClient);
    }

    /**
     * Create new {@link Theta} for plug-in.
     */
    @Nonnull
    public static Theta createForPlugin() {
        return new Theta(OSCClient.create(INTERNAL_ENDPOINT));
    }

    /**
     * Acquire the information of THETA.
     *
     * @return Information of THETA.
     * @throws IOException I/O error is occurred.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/protocols/info.html">Info · protocols · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public ThetaInfo info() throws IOException {
        return oscClient.info(ThetaInfo.class);
    }

    /**
     * Acquire the state of THETA.
     *
     * @return State of THETA.
     * @throws IOException I/O error is occurred.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/protocols/state.html">State · protocols · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public OSCState<ThetaState> state() throws IOException {
        return oscClient.state(ThetaState.class);
    }

    /**
     * Check for the updates of the state.
     *
     * @param fingerprint fingerprint of the last state.
     * @return updated fingerprint
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if fingerprint is null.
     * @see Theta#state()
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/protocols/check_for_updates.html">CheckForUpdates · protocols · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    private String checkForUpdate(@Nonnull final String fingerprint) throws IOException {
        Objects.requireNonNull(fingerprint, "fingerprint can not be null.");

        return oscClient.checkForUpdates(fingerprint);
    }

    /**
     * Check command status.
     *
     * @param response Last response of command execution.
     * @param <R>      Type of command result.
     * @return Updated response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if response is null.
     */
    @Nonnull
    public <R> CommandResponse<R> commandStatus(@Nonnull final CommandResponse<R> response) throws IOException {
        Objects.requireNonNull(response, "response can not be null.");

        return oscClient.commandStatus(response);
    }

    /**
     * Get single option value.
     *
     * @param option Option to get value.
     * @param <T>    Type of option value.
     * @return Acquired option value.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if option is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/">Overview · Options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public <T> T getOption(@Nonnull final Option<T> option) throws IOException {
        Objects.requireNonNull(option, "option can not be null.");

        return oscClient.getOption(option);
    }

    /**
     * Get single option value.
     *
     * @param option Option to get value.
     * @param <T>    Type of option value.
     * @return Acquired option value.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if option is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/">Overview · Options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public <T> List<T> getOption(@Nonnull final ArrayOption<T> option) throws IOException {
        Objects.requireNonNull(option, "option can not be null.");

        return oscClient.getOption(option);
    }

    /**
     * Get option values.
     *
     * @param options Options to get values.
     * @return Acquired option values.
     * @throws IOException              I/O error is occurred.
     * @throws OSCException             Server returned error response.
     * @throws NullPointerException     if options is null or contains null.
     * @throws IllegalArgumentException if options length less than 1.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/">Overview · Options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public OptionSet getOptions(@Nonnull final Option... options) throws IOException {
        return oscClient.getOptions(options);
    }

    /**
     * Get option values.
     *
     * @param options Options to get values.
     * @return Acquired option values.
     * @throws IOException              I/O error is occurred.
     * @throws OSCException             Server returned error response.
     * @throws NullPointerException     if options is null or contains null.
     * @throws IllegalArgumentException if options length less than 1.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/">Overview · Options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public OptionSet getOptions(@Nonnull final Collection<Option> options) throws IOException {
        return oscClient.getOptions(options);
    }

    /**
     * Set single option value.
     *
     * @param option Option to set value.
     * @param value  Value to set.
     * @param <T>    Type of option value.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if options is null or contains null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/">Overview · Options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public <T> void setOption(@Nonnull final Option<T> option, T value) throws IOException {
        Objects.requireNonNull(option, "option can not be null.");
        Objects.requireNonNull(value, "value can not be null.");

        oscClient.setOption(option, value);
    }

    /**
     * Set option values.
     *
     * @param optionSet Options to set.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if optionSet is null.
     * @see OptionSet.Builder
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/">Overview · Options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    public void setOptions(@Nonnull final OptionSet optionSet) throws IOException {
        Objects.requireNonNull(optionSet, "optionSet can not be null.");

        oscClient.setOptions(optionSet);
    }

    /**
     * Turns the wireless LAN off.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._finish_wlan.html">camera._finishWlan · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> finishWlan() throws IOException {
        return oscClient.commandExecute(Commands.FINISH_WLAN);
    }

    /**
     * Starts still image shooting.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.take_picture.html">camera.takePicture · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<TakePicture.Result> takePicture() throws IOException {
        return oscClient.commandExecute(Commands.TAKE_PICTURE);
    }

    /**
     * Starts continuous shooting.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.start_capture.html">camera.startCapture · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<StartCapture.Result> startCapture() throws IOException {
        return oscClient.commandExecute(Commands.START_CAPTURE);
    }

    /**
     * Starts continuous shooting.
     *
     * @param captureMode Capture mode.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if captureMode is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.start_capture.html">camera.startCapture · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<StartCapture.Result> startCapture(@Nonnull StartCapture.CaptureMode captureMode) throws IOException {
        Objects.requireNonNull(captureMode, "captureMode can not be null.");

        final StartCapture.Parameter parameters = new StartCapture.Parameter(captureMode);
        return oscClient.commandExecute(Commands.START_CAPTURE, parameters);
    }

    /**
     * Stops continuous shooting.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.stop_capture.html">camera.stopCapture · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<StopCapture.Result> stopCapture() throws IOException {
        return oscClient.commandExecute(Commands.STOP_CAPTURE);
    }

    /**
     * Acquires a list of still image files and movie files.
     *
     * @param parameter Command parameter.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if parameter is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.list_files.html">Acquires a list of still image files and movie files.</a>
     */
    @Nonnull
    public CommandResponse<ListFiles.Result> listFiles(@Nonnull final ListFiles.Parameter parameter) throws IOException {
        Objects.requireNonNull(parameter, "parameter can not be null.");

        return oscClient.commandExecute(Commands.LIST_FILES, parameter);
    }

    /**
     * Deletes still image or video files.
     *
     * @param fileUrls Files to delete from THETA.
     * @return Command response.
     * @throws IOException              I/O error is occurred.
     * @throws OSCException             Server returned error response.
     * @throws NullPointerException     if fileUrls is null or contains null.
     * @throws IllegalArgumentException if fileUrls length less than 1.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.delete.html">camera.delete · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> delete(@Nonnull final URL... fileUrls) throws IOException {
        return delete(Arrays.asList(fileUrls));
    }

    /**
     * Get live preview stream of the camera.
     * You must call {@link MJpegInputStream#close()} of returned value to terminate stream.
     *
     * @return Motion JPEG stream.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     */
    @Nonnull
    public MJpegInputStream getLivePreview() throws IOException {
        return oscClient.getLivePreview();
    }

    /**
     * Deletes still image or video files.
     *
     * @param fileUrls Files to delete from THETA.
     * @return Command response.
     * @throws IOException              I/O error is occurred.
     * @throws OSCException             Server returned error response.
     * @throws NullPointerException     if fileUrls is null or contains null.
     * @throws IllegalArgumentException if fileUrls length less than 1.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.delete.html">camera.delete · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> delete(@Nonnull final Collection<URL> fileUrls) throws IOException {
        Objects.requireNonNull(fileUrls, "fileUrls can not be null.");
        if (fileUrls.size() < 1) {
            throw new IllegalArgumentException("fileUrls must have 1 or more entries");
        }
        if (fileUrls.contains(null)) {
            throw new NullPointerException("fileUrls can not contain null.");
        }

        final Delete.Parameter parameter = new Delete.Parameter(fileUrls);
        return oscClient.commandExecute(Commands.DELETE, parameter);
    }

    /**
     * Shows the meta information for the specified still image.
     *
     * @param fileUrl File to acquire metadata.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if fileUrl is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._get_metadata.html">camera._getMetadata · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Metadata> getMetadata(@Nonnull final URL fileUrl) throws IOException {
        Objects.requireNonNull(fileUrl, "fileUrl can not be null.");

        final GetMetadata.Parameter parameter = new GetMetadata.Parameter(fileUrl);
        return oscClient.commandExecute(Commands.GET_METADATA, parameter);
    }

    /**
     * Reset all device settings and capture settings. After reset, the camera will be restarted.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.reset.html">camera.reset · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> reset() throws IOException {
        return oscClient.commandExecute(Commands.RESET);
    }

    /**
     * Acquires the default shooting property for boot time.
     *
     * @param captureMode Capture mode.
     * @param option      Option to acquire value.
     * @param <T>         Type of option.
     * @return Acquired property.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if argument is null or contains null.
     */
    @Nonnull
    public <T> T getMySetting(@Nonnull final CaptureMode captureMode, @Nonnull final Option<T> option) throws IOException {
        return getMySettings(captureMode, option).get(option);
    }

    /**
     * Acquires the default shooting properties for boot time.
     *
     * @param captureMode Capture mode.
     * @param options     Options to acquire values.
     * @return Acquired properties.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if argument is null or contains null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._get_my_setting.html">camera._getMySetting · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public OptionSet getMySettings(@Nonnull final CaptureMode captureMode, @Nonnull final Option... options) throws IOException {
        return getMySettings(captureMode, Arrays.asList(options));
    }

    /**
     * Acquires the default shooting properties for boot time.
     *
     * @param captureMode Capture mode.
     * @param options     Options to acquire values.
     * @return Acquired properties.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if argument is null or contains null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._get_my_setting.html">camera._getMySetting · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public OptionSet getMySettings(@Nonnull final CaptureMode captureMode, @Nonnull final Collection<Option> options) throws IOException {
        Objects.requireNonNull(captureMode, "captureMode can not be null.");
        Objects.requireNonNull(options, "options can not be null.");
        if (options.contains(null)) {
            throw new NullPointerException("options can not contain null.");
        }

        final List<String> optionNames = options.stream().map(Option::getName).collect(Collectors.toList());
        final GetMySetting.Parameter parameter = new GetMySetting.Parameter(captureMode, optionNames);
        return oscClient.commandExecute(Commands.GET_MY_SETTINGS, parameter).getResult();
    }

    /**
     * Set the default shooting properties for boot time.
     *
     * @param captureMode Capture mode.
     * @param option      Option to set value.
     * @param value       Value to set.
     * @param <T>         Type of option.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if argument is null or contains null.
     */
    @Nonnull
    public <T> void setMySetting(@Nonnull final CaptureMode captureMode, @Nonnull final Option<T> option, T value) throws IOException {
        Objects.requireNonNull(option, "option can not be null.");
        Objects.requireNonNull(value, "value can not be null.");

        final OptionSet optionSet = new OptionSet.Builder()
                .put(option, value)
                .build();
        setMySettings(captureMode, optionSet);
    }

    /**
     * Set the default shooting properties for boot time.
     *
     * @param captureMode Capture mode.
     * @param optionSet   Set of options to store.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if argument is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._set_my_setting.html">camera._setMySetting · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    public void setMySettings(@Nonnull final CaptureMode captureMode, @Nonnull final OptionSet optionSet) throws IOException {
        Objects.requireNonNull(captureMode, "captureMode can not be null.");
        Objects.requireNonNull(optionSet, "optionSet can not be null.");

        final SetMySetting.Parameter parameter = new SetMySetting.Parameter(captureMode, optionSet);
        oscClient.commandExecute(Commands.SET_MY_SETTINGS, parameter);
    }

    /**
     * Delete the default shooting properties for boot time.
     *
     * @param captureMode Capture mode to delete the settings.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if argument is null.
     */
    public void deleteMySettings(@Nonnull final CaptureMode captureMode) throws IOException {
        Objects.requireNonNull(captureMode, "captureMode can not be null.");

        final DeleteMySetting.Parameter parameter = new DeleteMySetting.Parameter(captureMode);
        oscClient.commandExecute(Commands.DELETE_MY_SETTING, parameter);
    }

    /**
     * Stop running self-timer.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._stop_self_timer.html">camera._stopSelfTimer · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> stopSelfTimer() throws IOException {
        return oscClient.commandExecute(Commands.STOP_SELF_TIMER);
    }

    /**
     * Converts the movie format of a saved movie.
     *
     * @param parameter Command parameter.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if parameter is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._convert_video_formats.html">camera._convertVideoFormats · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<ConvertVideoFormats.Result> convertVideoFormats(@Nonnull final ConvertVideoFormats.Parameter parameter) throws IOException {
        Objects.requireNonNull(parameter, "parameter can not be null.");

        return oscClient.commandExecute(Commands.CONVERT_VIDEO_FORMATS, parameter);
    }

    /**
     * Cancels the movie format conversion.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._cancel_video_convert.html">camera._cancelVideoConvert · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> cancelVideoConvert() throws IOException {
        return oscClient.commandExecute(Commands.CANCEL_VIDEO_CONVERT);
    }

    /**
     * Registers identification information (UUID) of a BLE device to the camera.
     *
     * @param uuid UUID for a BLE device.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if uuid is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._set_bluetooth_device.html">camera._setBluetoothDevice · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<SetBluetoothDevice.Result> setBluetoothDevice(@Nonnull final String uuid) throws IOException {
        Objects.requireNonNull(uuid, "uuid can not be null.");

        final SetBluetoothDevice.Parameter parameter = new SetBluetoothDevice.Parameter(uuid);
        return oscClient.commandExecute(Commands.SET_BLUETOOTH_DEVICE, parameter);
    }

    /**
     * Acquires the access point list used in client mode.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._list_access_points.html">camera._listAccessPoints · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<ListAccessPoints.Result> listAccessPoints() throws IOException {
        return oscClient.commandExecute(Commands.LIST_ACCESS_POINTS);
    }

    /**
     * Sets the access point information used in client mode.
     *
     * @param accessPoint Access point information to register.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if accessPoint is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._set_access_point.html">camera._setAccessPoint · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> setAccessPoint(@Nonnull final AccessPoint accessPoint) throws IOException {
        Objects.requireNonNull(accessPoint, "accessPoint can not be null.");

        return oscClient.commandExecute(Commands.SET_ACCESS_POINT, accessPoint);
    }

    /**
     * Deletes access point information used in client mode.
     *
     * @param ssid SSID of access point to delete.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if ssid is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._delete_access_point.html">camera._deleteAccessPoint · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> deleteAccessPoint(@Nonnull final String ssid) throws IOException {
        Objects.requireNonNull(ssid, "ssid can not be null.");

        final DeleteAccessPoint.Parameter parameter = new DeleteAccessPoint.Parameter(ssid);
        return oscClient.commandExecute(Commands.DELETE_ACCESS_POINT, parameter);
    }

    /**
     * Acquires a list of installed plugins.
     *
     * @return Command response.
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._list_plugins.html">camera._listPlugins · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<ListPlugins.Result> listPlugins() throws IOException {
        return oscClient.commandExecute(Commands.LIST_PLUGINS);
    }

    /**
     * Sets the installed pug-in for boot.
     *
     * @param packageName Package name of the plug-in for boot.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if packageName is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._set_plugin.html">camera._setPlugin · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> setPlugin(@Nonnull final String packageName) throws IOException {
        Objects.requireNonNull(packageName, "packageName can not be null.");

        final SetPlugin.Parameter parameter = new SetPlugin.Parameter(packageName, true);
        return oscClient.commandExecute(Commands.SET_PLUGIN, parameter);
    }

    /**
     * Controls the plug-in.
     *
     * @param action Action
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if action is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._plugin_control.html">camera._pluginControl · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> pluginControl(@Nonnull final PluginAction action) throws IOException {
        return pluginControl(action, null);
    }

    /**
     * Controls the plug-in.
     *
     * @param action      Action
     * @param packageName Package name of the plugin to control.
     * @return Command response.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if action is null.
     * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._plugin_control.html">camera._pluginControl · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
     */
    @Nonnull
    public CommandResponse<Void> pluginControl(@Nonnull final PluginAction action, @Nullable final String packageName) throws IOException {
        Objects.requireNonNull(action, "action can not be null.");

        final PluginControl.Parameter parameter = new PluginControl.Parameter(action, packageName);
        return oscClient.commandExecute(Commands.PLUGIN_CONTROL, parameter);
    }

    /**
     * Get the package names of the start-up plug-in.
     *
     * @throws IOException  I/O error is occurred.
     * @throws OSCException Server returned error response.
     */
    @Nonnull
    public CommandResponse<GetPluginOrders.Result> getPluginOrders() throws IOException {
        return oscClient.commandExecute(Commands.GET_PLUGIN_ORDERS);
    }

    /**
     * Set the package names of the start-up plug-in.
     *
     * @param packageNames List of package names of the start-up plug-in.
     * @throws IOException          I/O error is occurred.
     * @throws OSCException         Server returned error response.
     * @throws NullPointerException if packageNames is null or contains null.
     */
    @Nonnull
    public CommandResponse<Void> setPluginOrders(List<String> packageNames) throws IOException {
        Objects.requireNonNull(packageNames, "packageNames can not be null.");
        if (packageNames.contains(null)) {
            throw new NullPointerException("packageNames can not contain null.");
        }

        final SetPluginOrders.Parameter parameter = new SetPluginOrders.Parameter(packageNames);
        return oscClient.commandExecute(Commands.SET_PLUGIN_ORDERS, parameter);
    }
}
