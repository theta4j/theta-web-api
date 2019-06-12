package org.theta4j.webapi;

import com.google.gson.annotations.SerializedName;

/**
 * Shooting function mode supported by THETA.
 * Actual supported values are different between each models.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_function.html">_function · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum Function {
    /**
     * Normal shooting function
     */
    @SerializedName("normal") NORMAL,

    /**
     * Self-timer shooting function
     */
    @SerializedName("selfTimer") SELF_TIMER,

    /**
     * My setting shooting function
     */
    @SerializedName("mySetting") MY_SETTING
}
