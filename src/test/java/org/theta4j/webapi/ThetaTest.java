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

import org.junit.jupiter.api.*;
import org.theta4j.osc.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.theta4j.webapi.Options.*;

class ThetaTest {
    private static Theta theta;

    private static OptionSet backup;

    private static Set<URL> beforeFiles;

    private static <R> CommandResponse<R> waitForDone(CommandResponse<R> res) throws Exception {
        while (res.getState() != CommandState.DONE) {
            res = theta.commandStatus(res);
            Thread.sleep(100);
        }
        return res;
    }

    private static Set<URL> listFiles() throws Exception {
        final ListFiles.Parameter parameter = new ListFiles.Parameter.Builder(1000).build();
        final ListFiles.Result result = waitForDone(theta.listFiles(parameter)).getResult();
        return result.getEntries().stream().map(FileInfo::getFileUrl).collect(Collectors.toSet());
    }

    private static final List<Option> OPTIONS = Arrays.asList(
            APERTURE,
            AUTO_BRACKET,
            BITRATE,
            BLUETOOTH_POWER,
            CAPTURE_INTERVAL,
            CAPTURE_MODE,
            CAPTURE_NUMBER,
            CLIENT_VERSION,
            COLOR_TEMPERATURE,
            // COMPOSITE_SHOOTING_OUTPUT_INTERVAL,
            // COMPOSITE_SHOOTING_TIME,
            DATE_TIME_ZONE,
            EXPOSURE_COMPENSATION,
            EXPOSURE_DELAY,
            EXPOSURE_PROGRAM,
            FILE_FORMAT,
            FILTER,
            MICROPHONE_GAIN,
            GPS_INFORMATION,
            // HDMI_RESOLUTION,
            ISO,
            LATEST_ENABLED_EXPOSURE_DELAY_TIME,
            MAX_RECORDABLE_TIME,
            MICROPHONE,
            MICROPHONE_CHANNEL,
            NETWORK_TYPE,
            OFF_DELAY,
            PREVIEW_FORMAT,
            REMAINING_PICTURES,
            REMAINING_SPACE,
            REMAINING_VIDEO_SECONDS,
            SHUTTER_SPEED,
            SHUTTER_VOLUME,
            SLEEP_DELAY,
            TOTAL_SPACE,
            VIDEO_STITCHING,
            WHITE_BALANCE,
            // WLAN_CHANNEL,
            WLAN_FREQUENCY
    );

    private static final List<Option> SUPPORTS = Arrays.asList(
            APERTURE_SUPPORT,
            BRACKET_NUMBER_SUPPORT,
            BITRATE_SUPPORT,
            BLUETOOTH_POWER_SUPPORT,
            CAPTURE_INTERVAL_SUPPORT,
            CAPTURE_MODE_SUPPORT,
            CAPTURE_NUMBER_SUPPORT,
            COLOR_TEMPERATURE_SUPPORT,
            // COMPOSITE_SHOOTING_OUTPUT_INTERVAL_SUPPORT,
            // COMPOSITE_SHOOTING_TIME_SUPPORT,
            EXPOSURE_COMPENSATION_SUPPORT,
            EXPOSURE_DELAY_SUPPORT,
            EXPOSURE_PROGRAM_SUPPORT,
            FILE_FORMAT,
            FILTER_SUPPORT,
            MICROPHONE_GAIN_SUPPORT,
            // HDMI_RESOLUTION_SUPPORT,
            ISO_SUPPORT,
            MAX_RECORDABLE_TIME_SUPPORT,
            MICROPHONE_SUPPORT,
            MICROPHONE_CHANNEL_SUPPORT,
            NETWORK_TYPE_SUPPORT,
            OFF_DELAY_SUPPORT,
            PREVIEW_FORMAT_SUPPORT,
            SHUTTER_SPEED_SUPPORT,
            SHUTTER_VOLUME_SUPPORT,
            SLEEP_DELAY_SUPPORT,
            VIDEO_STITCHING_SUPPORT,
            WHITE_BALANCE_SUPPORT,
            WLAN_FREQUENCY_SUPPORT
    );

    @BeforeAll
    static void setup() throws Exception {
        theta = Theta.create();
        backup = theta.getOptions(OPTIONS);
        beforeFiles = listFiles();
    }

    @AfterAll
    static void restore() throws Exception {
        // restore options.
        theta.setOption(CAPTURE_MODE, backup.get(CAPTURE_MODE));
        theta.setOptions(new OptionSet.Builder()
                .put(APERTURE, backup.get(APERTURE))
                .put(AUTO_BRACKET, backup.get(AUTO_BRACKET))
                .put(BITRATE, backup.get(BITRATE))
                .put(BLUETOOTH_POWER, backup.get(BLUETOOTH_POWER))
                .put(CAPTURE_INTERVAL, backup.get(CAPTURE_INTERVAL))
                .put(CAPTURE_NUMBER, backup.get(CAPTURE_NUMBER))
                .put(CLIENT_VERSION, backup.get(CLIENT_VERSION))
                .put(COLOR_TEMPERATURE, backup.get(COLOR_TEMPERATURE))
                .put(EXPOSURE_COMPENSATION, backup.get(EXPOSURE_COMPENSATION))
                .put(EXPOSURE_DELAY, backup.get(EXPOSURE_DELAY))
                .put(EXPOSURE_PROGRAM, backup.get(EXPOSURE_PROGRAM))
                .put(FILE_FORMAT, backup.get(FILE_FORMAT))
                .put(FILTER, backup.get(FILTER))
                .put(MICROPHONE_GAIN, backup.get(MICROPHONE_GAIN))
                .put(GPS_INFORMATION, backup.get(GPS_INFORMATION))
                .put(ISO, backup.get(ISO))
                .put(MAX_RECORDABLE_TIME, backup.get(MAX_RECORDABLE_TIME))
                .put(MICROPHONE, backup.get(MICROPHONE))
                .put(MICROPHONE_CHANNEL, backup.get(MICROPHONE_CHANNEL))
                .put(OFF_DELAY, backup.get(OFF_DELAY))
                .put(PREVIEW_FORMAT, backup.get(PREVIEW_FORMAT))
                .put(SHUTTER_SPEED, backup.get(SHUTTER_SPEED))
                .put(SHUTTER_VOLUME, backup.get(SHUTTER_VOLUME))
                .put(SLEEP_DELAY, backup.get(SLEEP_DELAY))
                .put(VIDEO_STITCHING, backup.get(VIDEO_STITCHING))
                .put(WHITE_BALANCE, backup.get(WHITE_BALANCE))
                .put(WLAN_FREQUENCY, backup.get(WLAN_FREQUENCY))
                .build());

        // remove files created in test.
        final Set<URL> diffFiles = listFiles();
        diffFiles.removeAll(beforeFiles);
        if (diffFiles.size() >= 1) {
            waitForDone(theta.delete(diffFiles));
        }
    }

    @BeforeEach
    void reset() throws IOException {
        theta.setOption(CAPTURE_MODE, CaptureMode.IMAGE);
        theta.setOptions(new OptionSet.Builder()
                .put(APERTURE, Aperture.F2_0)
                .put(BLUETOOTH_POWER, BluetoothPower.ON)
                .put(CAPTURE_NUMBER, 0)
                .put(CLIENT_VERSION, ApiVersion.V2_1)
                .put(COLOR_TEMPERATURE, 5000)
                .put(EXPOSURE_COMPENSATION, ExposureCompensation._0_0)
                .put(EXPOSURE_DELAY, 0)
                .put(EXPOSURE_PROGRAM, ExposureProgram.AUTO)
                .put(FILE_FORMAT, FileFormat.JPEG_5376_2688)
                .put(FILTER, Filter.OFF)
                .put(MICROPHONE_GAIN, MicrophoneGain.NORMAL)
                .put(ISO, ISOSpeed.AUTO)
                .put(MICROPHONE, Microphone.AUTO)
                .put(MICROPHONE_CHANNEL, MicrophoneChannel.CH_1)
                .put(OFF_DELAY, 8 * 60 * 60)
                .put(PREVIEW_FORMAT, PreviewFormat._1920_960_8P)
                .put(SHUTTER_SPEED, ShutterSpeed.AUTO)
                .put(SHUTTER_VOLUME, 50)
                .put(SLEEP_DELAY, 65535)
                .put(VIDEO_STITCHING, VideoStitching.ON_DEVICE)
                .put(WHITE_BALANCE, WhiteBalance.AUTO)
                .put(WLAN_FREQUENCY, WlanFrequency.F2_4)
                .build());
    }

    @Test
    void testInfo() throws Exception {
        final ThetaInfo info = theta.info();
        System.out.println(info);
    }

    @Test
    void testState() throws Exception {
        final OSCState<ThetaState> oscState = theta.state();
        final ThetaState thetaState = oscState.getState();
        assertNotNull(oscState);
        assertNotNull(oscState.getFingerprint());
        assertNotNull(thetaState);
        System.out.println(thetaState);
    }

    @Nested
    class CommandTest {
        @Disabled
        @Test
        void finishWLan() throws Exception {
            waitForDone(theta.finishWlan());
        }

        @Test
        void testTakePicture() throws Exception {
            // execute command
            CommandResponse<TakePicture.Result> res = waitForDone(theta.takePicture());

            // check result
            final TakePicture.Result result = res.getResult();
            assertNotNull(result);
            assertNotNull(result.getFileUrl());
        }

        @Test
        void startIntervalCapture() throws Exception {
            theta.setOptions(new OptionSet.Builder()
                    .put(CAPTURE_MODE, CaptureMode.IMAGE)
                    .put(CAPTURE_NUMBER, 2)
                    .put(CAPTURE_INTERVAL, 4)
                    .build());

            // execute command with out _mode parameter. (default _mode is interval.)
            final CommandResponse<StartCapture.Result> res = waitForDone(theta.startCapture());

            // check result
            final StartCapture.Result result = res.getResult();
            assertNotNull(result);
            assertNotNull(result.getFileUrls());
        }

        @Test
        void startStopIntervalCapture() throws Exception {
            theta.setOptions(new OptionSet.Builder()
                    .put(CAPTURE_MODE, CaptureMode.IMAGE)
                    .put(CAPTURE_NUMBER, 0) // limitless
                    .put(CAPTURE_INTERVAL, 4)
                    .build());

            // start and stop
            theta.startCapture();
            final CommandResponse<StopCapture.Result> res = waitForDone(theta.stopCapture());

            // check result
            final StopCapture.Result result = res.getResult();
            assertNotNull(result);
            assertNotNull(result.getFileUrls());
        }

        @Test
        void startStopVideoCapture() throws Exception {
            theta.setOption(CAPTURE_MODE, CaptureMode.VIDEO);

            // start and stop
            theta.startCapture();
            Thread.sleep(3000);
            final CommandResponse<StopCapture.Result> stopRes = waitForDone(theta.stopCapture());

            // check result
            final StopCapture.Result stopResult = stopRes.getResult();
            assertNotNull(stopResult);
            assertNotNull(stopResult.getFileUrls());
        }

        @Test
        void listFiles() throws Exception {
            // tested on other test
        }

        @Test
        void delete() throws Exception {
            // tested on other test
        }

        @Test
        void getMetaData() throws Exception {
            final URL url = waitForDone(theta.takePicture()).getResult().getFileUrl();
            final CommandResponse<Metadata> res = theta.getMetadata(url);

            // check result
            final Metadata metadata = res.getResult();
            assertNotNull(metadata);
            assertNotNull(metadata.getExif());
            assertNotNull(metadata.getXmp());
        }

        @Disabled
        @Test
        void reset() throws Exception {
            waitForDone(theta.reset());
        }

        @Test
        void stopSelfTimer() throws Exception {
            theta.setOption(CAPTURE_MODE, CaptureMode.IMAGE);
            theta.setOption(EXPOSURE_DELAY, 10);

            theta.takePicture();

            waitForDone(theta.stopSelfTimer());

            theta.setOption(EXPOSURE_DELAY, 0);
        }

        @Nested
        class VideoConversion {
            @Test
            void convertVideoFormats() throws Exception {
                theta.setOption(CAPTURE_MODE, CaptureMode.VIDEO);

                // create video for test
                theta.startCapture();
                Thread.sleep(3000);
                final URL url = waitForDone(theta.stopCapture()).getResult().getFileUrls().get(0);

                // start convert and wait for done
                final ConvertVideoFormats.Parameter parameter = new ConvertVideoFormats.Parameter(
                        url,
                        ConvertVideoFormats.Size._1920_960,
                        ConvertVideoFormats.ProjectionType.EQUIRECTANGULAR,
                        ConvertVideoFormats.Codec.H264_MPEG4_AVC,
                        ConvertVideoFormats.TopBottomCorrectionType.APPLY);
                final CommandResponse<ConvertVideoFormats.Result> response =
                        waitForDone(theta.convertVideoFormats(parameter));

                // check result
                final ConvertVideoFormats.Result result = response.getResult();
                assertNotNull(result);
                assertNotNull(result.getFileUrl());
            }

            @Test
            void cancelVideoConvert() throws Exception {
                theta.setOption(CAPTURE_MODE, CaptureMode.VIDEO);

                // create video for test
                theta.startCapture();
                Thread.sleep(3000);
                final URL url = waitForDone(theta.stopCapture()).getResult().getFileUrls().get(0);

                // start convert
                final ConvertVideoFormats.Parameter parameter = new ConvertVideoFormats.Parameter(
                        url,
                        ConvertVideoFormats.Size._1920_960,
                        ConvertVideoFormats.ProjectionType.EQUIRECTANGULAR,
                        ConvertVideoFormats.Codec.H264_MPEG4_AVC,
                        ConvertVideoFormats.TopBottomCorrectionType.APPLY);
                theta.convertVideoFormats(parameter);

                // cancel convert
                waitForDone(theta.cancelVideoConvert());
            }
        }

        @Disabled
        @Test
        void setBluetoothDevice() throws Exception {
            final String uuid = UUID.randomUUID().toString();
            waitForDone(theta.setBluetoothDevice(uuid));
        }

        @Nested
        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
        class AccessPointTest {
            private final String ssid = "becb391a-0cb5-4242-bb67-fabe31f4"; // dummy SSID to add and remove.

            @Test
            @Order(1)
            void setAccessPoint() throws Exception {
                final AccessPoint accessPoint = new AccessPoint.Builder(ssid)
                        .security(AccessPoint.Security.NONE, null)
                        .build();
                waitForDone(theta.setAccessPoint(accessPoint));
            }

            @Test
            @Order(2)
            void listAccessPoint() throws Exception {
                final CommandResponse<ListAccessPoints.Result> response = waitForDone(theta.listAccessPoints());

                final List<AccessPoint> accessPoints = response.getResult().getAccessPoints();
                final List<String> ssids = accessPoints.stream().map(AccessPoint::getSsid).collect(Collectors.toList());
                assertTrue(ssids.contains(ssid));
            }

            @Test
            @Order(3)
            void deleteAccessPoint() throws Exception {
                waitForDone(theta.deleteAccessPoint(ssid));
            }
        }

        @Test
        void listAndSetPlugin() throws Exception {
            final CommandResponse<ListPlugins.Result> response = waitForDone(theta.listPlugins());

            final List<PluginInfo> pluginInfoList = response.getResult().getPlugins();

            final String backup = pluginInfoList.stream()
                    .filter(PluginInfo::isBoot)
                    .findFirst()
                    .get()
                    .getPackageName();

            final String toBoot = pluginInfoList.stream()
                    .filter(info -> !info.isBoot())
                    .findFirst()
                    .get()
                    .getPackageName();

            waitForDone(theta.setPlugin(toBoot));
            waitForDone(theta.setPlugin(backup));
        }

        @Test
        void bootAndFinishPlugin() throws Exception {
            waitForDone(theta.pluginControl(PluginAction.BOOT));
            Thread.sleep(1000);
            waitForDone(theta.pluginControl(PluginAction.FINISH));
        }
    }

    @Nested
    class OptionTest {
        @Test
        void getAllOptions() throws Exception {
            for (Option<?> option : OPTIONS) {
                System.out.print(option.getName() + ": ");
                final Object value = theta.getOption(option);
                System.out.println(value);
            }
        }

        @Test
        void getAllSupports() throws Exception {
            for (Option<?> option : SUPPORTS) {
                System.out.print(option.getName() + ": ");

                if (option instanceof ArrayOption) {
                    final List<?> value = theta.getOption((ArrayOption<?>) option);
                    System.out.println(value);
                    value.forEach(Assertions::assertNotNull);
                } else {
                    final Object value = theta.getOption(option);
                    System.out.println(value);
                    assertNotNull(value);
                }
            }
        }

        @Test
        void setAperture() throws Exception {
            final List<Aperture> apertureSupport = theta.getOption(APERTURE_SUPPORT);
            for (Aperture aperture : apertureSupport) {
                theta.setOption(APERTURE, aperture);
            }
        }

        @Test
        void setAutoBracket() throws Exception {
            final AutoBracketProgram program = new AutoBracketProgram.Builder()
                    .add(ISOSpeed._400, ShutterSpeed._1_250, 5100)
                    .add(ISOSpeed._320, ShutterSpeed._1_250, 5100)
                    .add(ISOSpeed._2500, ShutterSpeed._1_250, 5000)
                    .build();
            theta.setOption(AUTO_BRACKET, program);
        }

        @Test
        void setBitrate() throws Exception {
            for (CaptureMode mode : theta.getOption(CAPTURE_MODE_SUPPORT)) {
                theta.setOption(CAPTURE_MODE, mode);
                final List<Bitrate> support = theta.getOption(BITRATE_SUPPORT);
                for (Bitrate value : support) {
                    theta.setOption(BITRATE, value);
                    assertEquals(value, theta.getOption(BITRATE));
                }
            }
        }

        @Test
        void setBluetoothPower() throws Exception {
            for (BluetoothPower btPower : theta.getOption(BLUETOOTH_POWER_SUPPORT)) {
                theta.setOption(BLUETOOTH_POWER, btPower);
                Thread.sleep(1000); // it takes few seconds to turn Bluetooth off.
                assertEquals(btPower, theta.getOption(BLUETOOTH_POWER));
            }
        }

        @Test
        void setCaptureInterval() throws Exception {
            // get backup value, min value, and max value.
            final CaptureIntervalSupport support = theta.getOption(CAPTURE_INTERVAL_SUPPORT);
            final Integer min = support.getMinInterval();
            final Integer max = support.getMaxInterval();

            theta.setOption(CAPTURE_INTERVAL, min);
            assertEquals(min, theta.getOption(CAPTURE_INTERVAL));

            theta.setOption(CAPTURE_INTERVAL, support.getMaxInterval());
            assertEquals(max, theta.getOption(CAPTURE_INTERVAL));
        }

        @Test
        void setCaptureMode() throws Exception {
            for (CaptureMode mode : theta.getOption(CAPTURE_MODE_SUPPORT)) {
                theta.setOption(CAPTURE_MODE, mode);
                assertEquals(mode, theta.getOption(CAPTURE_MODE));
            }
        }

        @Test
        void setCaptureNumber() throws Exception {
            final CaptureNumberSupport support = theta.getOption(CAPTURE_NUMBER_SUPPORT);
            final Integer min = support.getMinNumber();
            final Integer max = support.getMaxNumber();
            final Integer limitless = support.getLimitless();

            theta.setOption(CAPTURE_NUMBER, min);
            assertEquals(min, theta.getOption(CAPTURE_NUMBER));

            theta.setOption(CAPTURE_NUMBER, max);
            assertEquals(max, theta.getOption(CAPTURE_NUMBER));

            theta.setOption(CAPTURE_NUMBER, limitless);
            assertEquals(limitless, theta.getOption(CAPTURE_NUMBER));
        }

        @Test
        void setClientVersion() throws Exception {
            theta.setOption(CLIENT_VERSION, ApiVersion.V2_1);
            assertEquals(ApiVersion.V2_1, theta.getOption(CLIENT_VERSION));
        }

        @Test
        void setColorTemperature() throws Exception {
            final ColorTemperatureSupport support = theta.getOption(COLOR_TEMPERATURE_SUPPORT);
            final int min = support.getMinTemperature();
            final int max = support.getMaxTemperature();
            final int step = support.getStepSize();

            for (Integer i = min; i <= max; i += step) {
                theta.setOption(COLOR_TEMPERATURE, i);
                assertEquals(i, theta.getOption(COLOR_TEMPERATURE));
            }
        }

        @Test
        void setDateTimeZone() throws Exception {
            final String backup = theta.getOption(DATE_TIME_ZONE);
            theta.setOption(DATE_TIME_ZONE, backup);
        }

        @Test
        void setExposureCompensation() throws Exception {
            final List<ExposureCompensation> support = theta.getOption(EXPOSURE_COMPENSATION_SUPPORT);
            for (ExposureCompensation bias : support) {
                theta.setOption(EXPOSURE_COMPENSATION, bias);
                assertEquals(bias, theta.getOption(EXPOSURE_COMPENSATION));
            }
        }

        @Test
        void setExposureDelay() throws Exception {
            for (CaptureMode mode : theta.getOption(CAPTURE_MODE_SUPPORT)) {
                theta.setOption(CAPTURE_MODE, mode);
                final List<Integer> support = theta.getOption(EXPOSURE_DELAY_SUPPORT);
                for (Integer value : support) {
                    theta.setOption(EXPOSURE_DELAY, value);
                    assertEquals(value, theta.getOption(EXPOSURE_DELAY));
                }
            }
        }

        @Test
        void setExposureProgram() throws Exception {
            theta.setOption(CAPTURE_MODE, CaptureMode.IMAGE);
            for (ExposureProgram program : theta.getOption(EXPOSURE_PROGRAM_SUPPORT)) {
                theta.setOption(EXPOSURE_PROGRAM, program);
                assertEquals(program, theta.getOption(EXPOSURE_PROGRAM));
            }
        }

        @Test
        void setFileFormat() throws Exception {
            for (CaptureMode mode : theta.getOption(CAPTURE_MODE_SUPPORT)) {
                theta.setOption(CAPTURE_MODE, mode);
                final FileFormat backup = theta.getOption(FILE_FORMAT);
                for (FileFormat format : theta.getOption(FILE_FORMAT_SUPPORT)) {
                    theta.setOption(FILE_FORMAT, format);
                    assertEquals(format, theta.getOption(FILE_FORMAT));
                }
                theta.setOption(FILE_FORMAT, backup);
            }
        }

        @Test
        void setFilter() throws Exception {
            for (Filter filter : theta.getOption(FILTER_SUPPORT)) {
                theta.setOption(FILTER, filter);
                assertEquals(filter, theta.getOption(FILTER));
            }
        }

        @Test
        void setMicrophoneGain() throws Exception {
            for (MicrophoneGain gain : theta.getOption(MICROPHONE_GAIN_SUPPORT)) {
                theta.setOption(MICROPHONE_GAIN, gain);
                assertEquals(gain, theta.getOption(MICROPHONE_GAIN));
            }
        }

        @Test
        void getGpsInformation() throws Exception {
            final GpsInfo gpsInfo = new GpsInfo(
                    new BigDecimal("34.885376"),
                    new BigDecimal("135.799788"),
                    new BigDecimal("45.41"),
                    "2014:05:18 01:04:29+08:00",
                    "WGS84");

            theta.setOption(GPS_INFORMATION, gpsInfo);
            assertEquals(gpsInfo, theta.getOption(GPS_INFORMATION));
        }

        @Test
        void setISO() throws Exception {
            theta.setOption(EXPOSURE_PROGRAM, ExposureProgram.ISO_SPEED);
            for (ISOSpeed iso : theta.getOption(ISO_SUPPORT)) {
                theta.setOption(ISO, iso);
                assertEquals(iso, theta.getOption(ISO));
            }
        }

        @Test
        void setMaxRecordableTime() throws Exception {
            for (Integer time : theta.getOption(MAX_RECORDABLE_TIME_SUPPORT)) {
                theta.setOption(MAX_RECORDABLE_TIME, time);
                assertEquals(time, theta.getOption(MAX_RECORDABLE_TIME));
            }
        }

        @Test
        void setMicrophone() throws Exception {
            for (Microphone mic : theta.getOption(MICROPHONE_SUPPORT)) {
                theta.setOption(MICROPHONE, mic);
                assertEquals(mic, theta.getOption(MICROPHONE));
            }
        }

        @Test
        void setMicrophoneChannel() throws Exception {
            for (MicrophoneChannel channel : theta.getOption(MICROPHONE_CHANNEL_SUPPORT)) {
                theta.setOption(MICROPHONE_CHANNEL, channel);
                assertEquals(channel, theta.getOption(MICROPHONE_CHANNEL));
            }
        }

        @Disabled
        @Test
        void setNetworkType() throws Exception {
            for (NetworkType type : theta.getOption(NETWORK_TYPE_SUPPORT)) {
                theta.setOption(NETWORK_TYPE, type);
                assertEquals(type, theta.getOption(NETWORK_TYPE));
            }
        }

        @Test
        void setOffDelay() throws Exception {
            for (Integer offDelay : theta.getOption(OFF_DELAY_SUPPORT)) {
                theta.setOption(OFF_DELAY, offDelay);
                assertEquals(offDelay, theta.getOption(OFF_DELAY));
            }
        }

        @Disabled
        @Test
        void setPassword() throws Exception {
            final String password = UUID.randomUUID().toString();
            theta.setOption(PASSWORD, password);
            // There is no way to retrieve and verify password.
        }

        @Test
        void setPreviewFormat() throws Exception {
            for (PreviewFormat format : theta.getOption(PREVIEW_FORMAT_SUPPORT)) {
                theta.setOption(PREVIEW_FORMAT, format);
                assertEquals(format, theta.getOption(PREVIEW_FORMAT));
            }
        }

        @Test
        void setShutterSpeed() throws Exception {
            final List<ExposureProgram> programs = Arrays.asList(ExposureProgram.MANUAL, ExposureProgram.SHUTTER_SPEED);
            for (ExposureProgram program : programs) {
                theta.setOption(EXPOSURE_PROGRAM, program);
                for (ShutterSpeed ss : theta.getOption(SHUTTER_SPEED_SUPPORT)) {
                    theta.setOption(SHUTTER_SPEED, ss);
                    assertEquals(ss, theta.getOption(SHUTTER_SPEED));
                }
            }
        }

        @Test
        void setShutterVolume() throws Exception {
            final ShutterVolumeSupport support = theta.getOption(SHUTTER_VOLUME_SUPPORT);
            final Integer min = support.getMinShutterVolume();
            final Integer max = support.getMaxShutterVolume();

            theta.setOption(SHUTTER_VOLUME, min);
            assertEquals(min, theta.getOptions(SHUTTER_VOLUME).get(SHUTTER_VOLUME));

            theta.setOption(SHUTTER_VOLUME, max);
            assertEquals(max, theta.getOptions(SHUTTER_VOLUME).get(SHUTTER_VOLUME));
        }

        @Test
        void setSleepDelay() throws Exception {
            for (Integer sleepDelay : theta.getOption(SLEEP_DELAY_SUPPORT)) {
                theta.setOption(SLEEP_DELAY, sleepDelay);
                assertEquals(sleepDelay, theta.getOption(SLEEP_DELAY));
            }
        }

        @Disabled
        @Test
        void setUsername() throws Exception {
            final String username = UUID.randomUUID().toString();
            theta.setOption(USERNAME, username);
            // There is no way to retrieve and verify username.
        }

        @Test
        void setVideoStitching() throws Exception {
            for (VideoStitching stitching : theta.getOption(VIDEO_STITCHING_SUPPORT)) {
                theta.setOption(VIDEO_STITCHING, stitching);
                assertEquals(stitching, theta.getOption(VIDEO_STITCHING));
            }
        }

        @Test
        void setWhiteBalance() throws Exception {
            for (WhiteBalance wb : theta.getOption(WHITE_BALANCE_SUPPORT)) {
                theta.setOption(WHITE_BALANCE, wb);
                assertEquals(wb, theta.getOption(WHITE_BALANCE));
            }
        }

        @Test
        void setWlanFrequency() throws Exception {
            for (WlanFrequency frequency : theta.getOption(WLAN_FREQUENCY_SUPPORT)) {
                theta.setOption(WLAN_FREQUENCY, frequency);
                assertEquals(frequency, theta.getOption(WLAN_FREQUENCY));
            }
        }
    }
}
