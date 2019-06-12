package org.theta4j.webapi;

final class DeleteMySetting {
    static final class Parameter {
        private final CaptureMode mode;

        Parameter(CaptureMode mode) {
            this.mode = mode;
        }
    }

    private DeleteMySetting() {
        throw new AssertionError();
    }
}
