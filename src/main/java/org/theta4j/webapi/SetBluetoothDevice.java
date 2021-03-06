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

import javax.annotation.Nonnull;

/**
 * camera._setBluetoothDevice command
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._set_bluetooth_device.html">camera._setBluetoothDevice · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class SetBluetoothDevice {
    static final class Parameter {
        private final String uuid;

        Parameter(final String uuid) {
            this.uuid = uuid;
        }
    }

    /**
     * Result of camera._setBluetoothDevice command.
     */
    public static final class Result {
        private final String deviceName;

        /**
         * Device name.
         */
        @Nonnull
        public String getDeviceName() {
            return deviceName;
        }

        /**
         * for GSON
         */
        private Result(final String deviceName) {
            this.deviceName = deviceName;
        }
    }

    private SetBluetoothDevice() {
        throw new AssertionError();
    }
}
