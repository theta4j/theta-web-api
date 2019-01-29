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
import javax.annotation.Nullable;
import java.math.BigDecimal;

/**
 * CommandResponse represents a response of Commands/Execute defined in Open Spherical Camera API.
 *
 * @param <R> Type of result value.
 * @see <a href="https://developers.google.com/streetview/open-spherical-camera/guides/osc/commands/execute">Execute | Open Spherical Camera API</a>
 */
public final class CommandResponse<R> {
    /**
     * Command progress description.
     */
    public static final class Progress {
        private BigDecimal completion;

        /**
         * Progress rate of the command executed.
         */
        public BigDecimal getCompletion() {
            return completion;
        }
    }

    private static final Gson GSON = new Gson();

    private final Class<R> resultType;

    private final String name;
    private final CommandState state;
    private final String id;
    private final R result;
    private final OSCException error;
    private final Progress progress;

    private CommandResponse(final Class<R> resultType, final String name, final CommandState state, final String id, final R result, final OSCException error, final Progress progress) {
        this.resultType = resultType;

        this.name = name;
        this.state = state;
        this.id = id;
        this.result = result;
        this.error = error;
        this.progress = progress;
    }

    static <R> CommandResponse<R> valueOf(final JsonObject json, Class<R> resultsType) {
        final String name = json.get("name").getAsString();
        final CommandState state = GSON.fromJson(json.get("state"), CommandState.class);
        final String newID = json.has("id") ?
                json.get("id").getAsString() : null;
        final R result = json.has("results") ?
                GSON.fromJson(json.get("results"), resultsType) : null;
        final OSCException error = json.has("error") ?
                GSON.fromJson(json.get("error"), OSCException.class) : null;
        final CommandResponse.Progress progress = json.has("progress") ?
                GSON.fromJson(json.get("progress"), CommandResponse.Progress.class) : null;

        return new CommandResponse<>(resultsType, name, state, newID, result, error, progress);
    }

    /**
     * Class object of the result type.
     */
    @Nonnull
    public Class<R> getResultType() {
        return resultType;
    }

    /**
     * The command name to be executed.
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * State of the command.
     */
    @Nonnull
    public CommandState getState() {
        return state;
    }

    /**
     * Command ID. This is available when {@link #getState()} is {@link CommandState#IN_PROGRESS}.
     */
    @Nullable
    public String getID() {
        return id;
    }

    /**
     * Command results.
     * This is null if the command does not have result or {@link #getState()} is not {@link CommandState#DONE}.
     */
    @Nullable
    public R getResult() {
        return result;
    }

    @Nullable
    OSCException getError() {
        return error;
    }

    /**
     * Command progress description. This is available when {@link #getState()} is {@link CommandState#IN_PROGRESS}.
     */
    @Nullable
    public Progress getProgress() {
        return progress;
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommandResponse{");
        sb.append("resultType=").append(resultType);
        sb.append(", name='").append(name).append('\'');
        sb.append(", state=").append(state);
        sb.append(", id='").append(id).append('\'');
        sb.append(", result=").append(result);
        sb.append(", error=").append(error);
        sb.append(", progress=").append(progress);
        sb.append('}');
        return sb.toString();
    }
}
