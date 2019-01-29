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

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.theta4j.osc.GsonUtils;

import javax.annotation.Nonnull;
import java.net.URL;
import java.util.Objects;

/**
 * File information.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.list_files.html">camera.listFiles · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class FileInfo {
    @Nonnull
    public enum ProjectionType {
        @SerializedName("Equirectangular") EQUIRECTANGULAR,
        @SerializedName("Dual-Fisheye") DUAL_FISHEYE
    }

    private String name;

    private URL fileUrl;

    private int size;

    private String dateTimeZone;

    private String dateTime;

    private int lat;

    private int lng;

    private int width;

    private int height;

    private String thumbnail;

    @SerializedName("_thumbSize")
    private int thumbSize;

    @SerializedName("_intervalCaptureGroupId")
    private String intervalCaptureGroupId;

    @SerializedName("_compositeShootingGroupId")
    private String compositeShootingGroupId;

    @SerializedName("_autoBracketGroupId")
    private String autoBracketGroupId;

    @SerializedName("_recordTime")
    private int recordTime;

    private boolean isProcessed;

    @JsonAdapter(GsonUtils.EmptyStringAsNullAdapter.class)
    private URL previewUrl;

    @SerializedName("_codec")
    private String codec;

    @SerializedName("_projectionType")
    private ProjectionType projectionType;

    // Getter

    /**
     * File name.
     */
    public String getName() {
        return name;
    }

    /**
     * File URL.
     */
    public URL getFileUrl() {
        return fileUrl;
    }

    /**
     * Size in bytes.
     */
    public int getSize() {
        return size;
    }

    /**
     * Time zone. Can be acquired when {@link Theta#listFiles(ListFiles.Parameter)} is executed with detail option.
     */
    public String getDateTimeZone() {
        return dateTimeZone;
    }

    /**
     * Timestamp of creation or update in localtime.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Latitude. Can be obtained for data shot with GPS enabled.
     */
    public int getLat() {
        return lat;
    }

    /**
     * Longitude. Can be obtained for data shot with GPS enabled.
     */
    public int getLng() {
        return lng;
    }

    /**
     * Image width in pixels.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Image height in pixels.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Thumbnail data encoded in Base64.
     * Can be acquired when {@link Theta#listFiles(ListFiles.Parameter)} is executed with maxThumbSize option.
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Thumbnail size in bytes.
     * Can be acquired when {@link Theta#listFiles(ListFiles.Parameter)} is executed with maxThumbSize option.
     */
    public int getThumbSize() {
        return thumbSize;
    }

    /**
     * Group ID of a still image shot by interval shooting.
     * Can be obtained if the still image was shot by interval shooting.
     */
    public String getIntervalCaptureGroupId() {
        return intervalCaptureGroupId;
    }

    /**
     * Group ID of a still image shot by interval composite shooting.
     * Can be obtained if the still image was shot by interval composite shooting.
     */
    public String getCompositeShootingGroupId() {
        return compositeShootingGroupId;
    }

    /**
     * Group ID of a still image shot by multi bracket shooting.
     * Can be obtained if the still image was shot by multi bracket shooting.
     */
    public String getAutoBracketGroupId() {
        return autoBracketGroupId;
    }

    /**
     * Video shooting time in seconds.
     */
    public int getRecordTime() {
        return recordTime;
    }

    /**
     * True if image processing has been completed.
     */
    public boolean isProcessed() {
        return isProcessed;
    }

    /**
     * URL of the file in processing.
     */
    public URL getPreviewUrl() {
        return previewUrl;
    }

    /**
     * Video codec.
     */
    public String getCodec() {
        return codec;
    }

    /**
     * Projection type.
     */
    public ProjectionType getProjectionType() {
        return projectionType;
    }

    /**
     * Returns true if the all properties of this object and given objects are equaled.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return size == fileInfo.size &&
                lat == fileInfo.lat &&
                lng == fileInfo.lng &&
                width == fileInfo.width &&
                height == fileInfo.height &&
                thumbSize == fileInfo.thumbSize &&
                recordTime == fileInfo.recordTime &&
                isProcessed == fileInfo.isProcessed &&
                Objects.equals(name, fileInfo.name) &&
                Objects.equals(fileUrl, fileInfo.fileUrl) &&
                Objects.equals(dateTimeZone, fileInfo.dateTimeZone) &&
                Objects.equals(dateTime, fileInfo.dateTime) &&
                Objects.equals(thumbnail, fileInfo.thumbnail) &&
                Objects.equals(intervalCaptureGroupId, fileInfo.intervalCaptureGroupId) &&
                Objects.equals(compositeShootingGroupId, fileInfo.compositeShootingGroupId) &&
                Objects.equals(autoBracketGroupId, fileInfo.autoBracketGroupId) &&
                Objects.equals(previewUrl, fileInfo.previewUrl) &&
                Objects.equals(codec, fileInfo.codec) &&
                projectionType == fileInfo.projectionType;
    }

    /**
     * Returns hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, fileUrl, size, dateTimeZone, dateTime, lat, lng, width, height, thumbnail, thumbSize, intervalCaptureGroupId, compositeShootingGroupId, autoBracketGroupId, recordTime, isProcessed, previewUrl, codec, projectionType);
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", fileUrl=").append(fileUrl);
        sb.append(", size=").append(size);
        sb.append(", dateTimeZone='").append(dateTimeZone).append('\'');
        sb.append(", dateTime='").append(dateTime).append('\'');
        sb.append(", lat=").append(lat);
        sb.append(", lng=").append(lng);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", thumbnail='").append(thumbnail).append('\'');
        sb.append(", thumbSize=").append(thumbSize);
        sb.append(", intervalCaptureGroupId='").append(intervalCaptureGroupId).append('\'');
        sb.append(", compositeShootingGroupId='").append(compositeShootingGroupId).append('\'');
        sb.append(", autoBracketGroupId='").append(autoBracketGroupId).append('\'');
        sb.append(", recordTime=").append(recordTime);
        sb.append(", isProcessed=").append(isProcessed);
        sb.append(", previewUrl=").append(previewUrl);
        sb.append(", codec='").append(codec).append('\'');
        sb.append(", projectionType=").append(projectionType);
        sb.append('}');
        return sb.toString();
    }
}
