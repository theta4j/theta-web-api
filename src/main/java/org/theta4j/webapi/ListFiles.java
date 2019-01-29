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
import javax.annotation.Nullable;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * camera.listFiles command.
 *
 * @see <a href="https://developers.theta360.com/en/docs/v2.1/api_reference/commands/camera.list_files.html">camera.listFiles · commands · API Reference · v2.1 · API &amp; SDK | RICOH THETA Developers</a>
 */
public final class ListFiles {
    /**
     * File type.
     */
    public enum FileType {
        /**
         * Image files and video files.
         */
        @SerializedName("all") ALL,

        /**
         * Image files.
         */
        @SerializedName("image") IMAGE,

        /**
         * Video files.
         */
        @SerializedName("video") VIDEO;
    }

    /**
     * Sort order.
     */
    public enum SortOrder {
        /**
         * Sort by newest to oldest.
         */
        @SerializedName("newest") NEWEST,

        /**
         * Sort by oldest to newest.
         */
        @SerializedName("oldest") OLDEST;
    }

    /**
     * Parameter of camera.listFiles command.
     */
    public static final class Parameter {
        private FileType fileType;

        private Integer startPosition;

        @SerializedName("_startFileUrl")
        private URL startFileUrl;

        private int entryCount;

        private Integer maxThumbSize;

        @SerializedName("_detail")
        private Boolean detail;

        @SerializedName("_sort")
        private SortOrder sortOrder;

        /**
         * Create new parameter.
         *
         * @param fileType      File type filter.
         * @param startPosition Start position.
         * @param startFileUrl  Start file URL.
         * @param entryCount    Max entry count.
         * @param maxThumbSize  Max thumbnail size in width pixels. Set null if a thumbnail is not need.
         * @param detail        If set to "false", "name", "fileUrl", "size", and "dateTime" can only be acquired.
         * @param sortOrder     Sort order.
         * @throws NullPointerException if fileType is null.
         */
        public Parameter(
                @Nonnull final FileType fileType,
                @Nullable final Integer startPosition,
                @Nullable final URL startFileUrl,
                @Nullable final Integer entryCount,
                @Nullable final Integer maxThumbSize,
                @Nullable final Boolean detail,
                @Nullable final SortOrder sortOrder) {
            this.fileType = Objects.requireNonNull(fileType, "fileType can not be null.");
            this.startPosition = startPosition;
            this.startFileUrl = startFileUrl;
            this.entryCount = entryCount;
            this.maxThumbSize = maxThumbSize;
            this.detail = detail;
            this.sortOrder = sortOrder;
        }

        /**
         * Builder of {@link ListFiles.Parameter}.
         */
        public static final class Builder {
            private final int entryCount;

            private FileType fileType = FileType.ALL;

            private Integer startPosition;

            private URL startFileUrl;

            private Integer maxThumbSize;

            private Boolean detail;

            private SortOrder sortOrder;

            /**
             * Create new builder.
             *
             * @param entryCount max entry count to acquire.
             */
            public Builder(final int entryCount) {
                this.entryCount = entryCount;
            }

            /**
             * Set start position.
             *
             * @param startPosition start position to set.
             * @return Builder instance.
             */
            @Nonnull
            public Builder startPosition(final int startPosition) {
                this.startPosition = startPosition;
                return this;
            }

            /**
             * Set start file URL.
             *
             * @param startFileUrl start file URL to set.
             * @return Builder instance.
             * @throws NullPointerException if startFileUrl is null.
             */
            @Nonnull
            public Builder startFileUrl(@Nonnull final URL startFileUrl) {
                this.startFileUrl = Objects.requireNonNull(startFileUrl, "startFileUrl can not be null.");
                return this;
            }

            /**
             * Set max thumbnail size in width pixels.
             * Do not call this method if thumbnail is not need.
             *
             * @param maxThumbSize max thumbnail size to set.
             * @return Builder instance.
             */
            @Nonnull
            public Builder maxThumbSize(final int maxThumbSize) {
                this.maxThumbSize = maxThumbSize;
                return this;
            }

            /**
             * Set detail option.
             *
             * @param detail If set to "false", "name", "fileUrl", "size", and "dateTime" can only be acquired.
             * @return Builder instance.
             */
            @Nonnull
            public Builder detail(final boolean detail) {
                this.detail = detail;
                return this;
            }

            /**
             * Set sort order.
             *
             * @param sortOrder sort order to set.
             * @return Builder instance.
             * @throws NullPointerException if sortOrder is null.
             */
            @Nonnull
            public Builder sortType(@Nonnull final SortOrder sortOrder) {
                this.sortOrder = Objects.requireNonNull(sortOrder, "sortOrder can not be null.");
                return this;
            }

            @Nonnull
            public Parameter build() {
                return new Parameter(fileType, startPosition, startFileUrl, entryCount, maxThumbSize, detail, sortOrder);
            }
        }

        /**
         * File type.
         */
        @Nonnull
        public FileType getFileType() {
            return fileType;
        }

        /**
         * Start position.
         */
        @Nullable
        public Integer getStartPosition() {
            return startPosition;
        }

        /**
         * Start file URL.
         */
        @Nullable
        public URL getStartFileUrl() {
            return startFileUrl;
        }

        /**
         * Max entry count to acquire.
         */
        public int getEntryCount() {
            return entryCount;
        }

        /**
         * Max thumbnail size in width pixels. null means thumbnail will not be acquired.
         */
        @Nullable
        public Integer getMaxThumbSize() {
            return maxThumbSize;
        }

        /**
         * If set to "false", "name", "fileUrl", "size", and "dateTime" can only be acquired.
         */
        @Nullable
        public Boolean getDetail() {
            return detail;
        }

        /**
         * Sort order.
         */
        @Nullable
        public SortOrder getSortOrder() {
            return sortOrder;
        }
    }

    /**
     * Result of camera.listFiles command.
     */
    public static final class Result {
        private final List<FileInfo> entries;

        private final int totalEntries;

        /**
         * Acquired file information list.
         */
        @Nonnull
        public List<FileInfo> getEntries() {
            return entries;
        }

        /**
         * Number of total entries.
         */
        public int getTotalEntries() {
            return totalEntries;
        }

        /**
         * for GSON
         */
        private Result(final List<FileInfo> entries, final int totalEntries) {
            this.entries = entries;
            this.totalEntries = totalEntries;
        }
    }

    private ListFiles() {
        throw new AssertionError();
    }
}
