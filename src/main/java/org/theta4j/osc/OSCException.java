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

import javax.annotation.Nonnull;

/**
 * OSCException represents OSC standard error object.
 *
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/error-handling">Error handling | Open Spherical Camera API</a>
 */
public final class OSCException extends RuntimeException {
    private String code;

    private String message;

    private OSCException() {
    }

    /**
     * Error Code.
     */
    @Nonnull
    public String getCode() {
        return code;
    }

    /**
     * Message of error.
     */
    @Override
    @Nonnull
    public String getMessage() {
        return message;
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("OSCException{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
