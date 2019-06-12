package org.theta4j.webapi;

import com.google.gson.annotations.SerializedName;

/**
 * Shooting method for My Settings mode.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_shooting_method.html">_shootingMethod · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum ShootingMethod {
    /**
     * Normal shooting.
     */
    @SerializedName("normal") NORMAL,

    /**
     * Interval shooting.
     */
    @SerializedName("interval") INTERVAL,

    /**
     * Multi bracket shooting.
     */
    @SerializedName("bracket") BRACKET,

    /**
     * Interval composite shooting.
     */
    @SerializedName("composite") COMPOSITE
}
