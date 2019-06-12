package org.theta4j.webapi;

import com.google.gson.annotations.SerializedName;

/**
 * Top/bottom correction for still image.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_top_bottom_correction.html">_topBottomCorrection · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum TopBottomCorrection {
    /**
     * Top/bottom correction is performed.
     */
    @SerializedName("Apply") APPLY,

    /**
     * Top/bottom correction is performed.
     * However, for Interval shooting, the parameters used for top/bottom correction for the first image are saved and used for the 2nd and subsequent images.
     */
    @SerializedName("ApplyAuto") APPLY_AUTO,

    /**
     * Performs top/bottom correction and then saves the parameters.
     */
    @SerializedName("ApplySave") APPLY_SAVE,

    /**
     * Performs top/bottom correction using the saved parameters.
     */
    @SerializedName("ApplyLoad") APPLY_LOAD,

    /**
     * Does not perform top/bottom correction.
     */
    @SerializedName("Disapply") DISAPPLY
}
