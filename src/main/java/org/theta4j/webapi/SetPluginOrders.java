package org.theta4j.webapi;

import java.util.Collections;
import java.util.List;

final class SetPluginOrders {
    static final class Parameter {
        private final List<String> pluginOrders;

        Parameter(List<String> pluginOrders) {
            this.pluginOrders = Collections.unmodifiableList(pluginOrders);
        }
    }

    private SetPluginOrders() {
        throw new AssertionError();
    }
}
