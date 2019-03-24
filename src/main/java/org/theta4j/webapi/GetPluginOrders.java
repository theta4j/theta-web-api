package org.theta4j.webapi;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * camera._getPluginOrders command.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera._get_plugin_orders.html">camera._getPluginOrders · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class GetPluginOrders {
    /**
     * Result of camera._getPluginOrders command.
     */
    public static final class Result {
        private final List<String> pluginOrders;

        /**
         * List of three package names of the start-up plug-in.
         */
        @Nonnull
        public List<String> getPluginOrders() {
            return pluginOrders;
        }

        /**
         * for GSON
         */
        private Result(List<String> pluginOrders) {
            this.pluginOrders = pluginOrders;
        }
    }

    private GetPluginOrders() {
        throw new AssertionError();
    }
}
