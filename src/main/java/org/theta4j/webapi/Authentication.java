package org.theta4j.webapi;

import com.google.gson.annotations.SerializedName;

/**
 * Authentication mode supported by THETA.
 * Actual supported values are different between each models.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_authentication.html">_authentication · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum Authentication {
    /**
     * No authentication is required.
     */
    @SerializedName("none") NONE,

    /**
     * Digest authentication is required.
     */
    @SerializedName("digest") DIGEST
}
