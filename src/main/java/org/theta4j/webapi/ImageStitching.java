package org.theta4j.webapi;

import com.google.gson.annotations.SerializedName;

/**
 * Still image stitching mode.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/options/_image_stitching.html">_imageStitching · options · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public enum ImageStitching {
    /**
     * Automatic.
     */
    @SerializedName("auto") AUTO,

    /**
     * Performs static stitching.
     */
    @SerializedName("static") STATIC,

    /**
     * Performs dynamic stitching.
     */
    @SerializedName("dynamicAuto") DYNAMIC_AUTO,

    /**
     * Performs dynamic stitching and then saves distortion correction parameters.
     */
    @SerializedName("dynamicSave") DYNAMIC_SAVE,

    /**
     * Performs stitching using the saved distortion correction parameters.
     */
    @SerializedName("dynamicLoad") DYNAMIC_LOAD,

    /**
     * Does not perform stitching.
     */
    @SerializedName("none") NONE;
}
