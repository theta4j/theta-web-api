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

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * AutoBracketProgram represents _autoBracket option.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_auto_bracket.html">_autoBracket · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class AutoBracketProgram {
    /**
     * Auto Bracket parameter.
     */
    public static final class Parameter {
        private final ISOSpeed iso;

        private final ShutterSpeed shutterSpeed;

        @SerializedName("_colorTemperature")
        private final int colorTemperature;

        /**
         * Create new parameter.
         *
         * @param iso              ISO speed.
         * @param shutterSpeed     Shutter speed.
         * @param colorTemperature Color temperature.
         * @throws NullPointerException if iso or shutterSpeed is null.
         */
        public Parameter(@Nonnull final ISOSpeed iso, @Nonnull final ShutterSpeed shutterSpeed, final int colorTemperature) {
            this.iso = Objects.requireNonNull(iso, "iso can not be null.");
            this.shutterSpeed = Objects.requireNonNull(shutterSpeed, "shutterSpeed can not be null.");
            this.colorTemperature = colorTemperature;
        }

        /**
         * ISO Speed.
         */
        @Nonnull
        public ISOSpeed getIso() {
            return iso;
        }

        /**
         * Shutter speed.
         */
        @Nonnull
        public ShutterSpeed getShutterSpeed() {
            return shutterSpeed;
        }

        /**
         * Color temperature.
         */
        public int getColorTemperature() {
            return colorTemperature;
        }

        /**
         * Returns true if the all properties of this object and given objects are equaled.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Parameter parameter = (Parameter) o;
            return colorTemperature == parameter.colorTemperature &&
                    iso == parameter.iso &&
                    shutterSpeed == parameter.shutterSpeed;
        }

        /**
         * Returns hashcode of this object.
         */
        @Override
        public int hashCode() {
            return Objects.hash(iso, shutterSpeed, colorTemperature);
        }

        /**
         * Returns String representation of this object.
         */
        @Override
        @Nonnull
        public String toString() {
            final StringBuilder sb = new StringBuilder("Parameter{");
            sb.append("iso=").append(iso);
            sb.append(", shutterSpeed=").append(shutterSpeed);
            sb.append(", colorTemperature=").append(colorTemperature);
            sb.append('}');
            return sb.toString();
        }
    }

    @SerializedName("_bracketNumber")
    private final int number;

    @SerializedName("_bracketParameters")
    private final List<Parameter> parameters;

    /**
     * Create new auto bracket program.
     *
     * @param parameters parameter list of the program.
     * @throws NullPointerException     if parameters is null, or contains null.
     * @throws IllegalArgumentException if parameter length is less than 1.
     */
    public AutoBracketProgram(@Nonnull final Parameter... parameters) {
        Objects.requireNonNull(parameters, "parameters can not be null.");
        this.number = parameters.length;

        this.parameters = Collections.unmodifiableList(Arrays.asList(parameters));
        if (this.parameters.isEmpty()) {
            throw new IllegalArgumentException("parameters must have 1 or more entries.");
        }
        if (this.parameters.contains(null)) {
            throw new NullPointerException("parameters can not contain null");
        }
    }

    /**
     * Number of parameters in this program.
     */
    public int getNumber() {
        return number;
    }

    /**
     * List of parameters in this program.
     */
    @Nonnull
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoBracketProgram that = (AutoBracketProgram) o;
        return number == that.number &&
                parameters.equals(that.parameters);
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(number, parameters);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("AutoBracketProgram{");
        sb.append("number=").append(number);
        sb.append(", parameters=").append(parameters);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Builder of {@link AutoBracketProgram}.
     */
    public static final class Builder {
        private final List<Parameter> parameters = new ArrayList<>();

        /**
         * Add new parameter.
         *
         * @param parameter parameter to add.
         * @return Builder instance.
         * @throws NullPointerException if parameter is null.
         */
        @Nonnull
        public Builder add(@Nonnull final Parameter parameter) {
            Objects.requireNonNull(parameter, "parameter can not be null.");
            parameters.add(parameter);
            return this;
        }

        /**
         * Add new parameter.
         *
         * @param iso              ISO speed of new parameter.
         * @param shutterSpeed     Shutter speed of new parameter.
         * @param colorTemperature Color temperature of new parameter.
         * @return Builder instance.
         * @throws NullPointerException if iso or shutterSpeed is null.
         */
        @Nonnull
        public Builder add(@Nonnull final ISOSpeed iso, @Nonnull final ShutterSpeed shutterSpeed, final int colorTemperature) {
            parameters.add(new Parameter(iso, shutterSpeed, colorTemperature));
            return this;
        }

        /**
         * Create AutoBracketProgram from this builder.
         */
        @Nonnull
        public AutoBracketProgram build() {
            return new AutoBracketProgram(parameters.toArray(new Parameter[]{}));
        }
    }
}
