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

import com.google.gson.annotations.SerializedName;

/**
 * CommandStates represents a state of the command exected.
 *
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/commands/execute">Execute | Open Spherical Camera API</a>
 */
public enum CommandState {
    /**
     * The command is done.
     */
    @SerializedName("done") DONE,

    /**
     * The command is in Progress.
     */
    @SerializedName("inProgress") IN_PROGRESS,

    /**
     * The command faced error.
     */
    @SerializedName("error") ERROR
}
