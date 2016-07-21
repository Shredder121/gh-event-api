/*
 * Copyright 2016 Shredder121.
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
package com.github.shredder121.gh_event_api.model;

import java.time.ZonedDateTime;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;
import com.google.common.collect.ImmutableList;

/**
 * A release is the tagging of a particular version.
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.PROTECTED,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class Release {

    /**
     * The id of the release, for GitHub bookkeeping.
     */
    Integer id;

    /**
     * The (API) URL to view this release.
     */
    String url;

    /**
     * The (GitHub Web UI) URL to view this release.
     */
    String htmlUrl;

    /**
     * The (API) URL to view this releases's assets.
     */
    String assetsUrl;

    /**
     * The (API) URL to upload additional assets.
     */
    String uploadUrl;

    /**
     * Text describing the contents of the tag.
     */
    String body;

    /**
     * The user that performed the release.
     */
    User author;

    /**
     * The name of the tag.
     */
    String tagName;

    /**
     * The commitish value that determines where the Git tag is created from.
     */
    String targetCommitish;

    /**
     * A list with assets.
     */
    ImmutableList<Asset> assets;

    /**
     * The date this release was created.
     */
    ZonedDateTime createdAt;

    /**
     * The date this release was created.
     */
    ZonedDateTime publishedAt;

    /**
     * Whether the release is a published one.
     */
    Boolean draft;

    /**
     * Whether the release is a prerelease or a full release.
     */
    Boolean prerelease;

    /**
     * An asset is an additional resource bundled with a release.
     */
    @lombok.Value
    @JsonNaming(LowerCaseWithUnderscoresStrategy.class)
    @lombok.RequiredArgsConstructor(
            access = lombok.AccessLevel.PROTECTED,
            onConstructor = @__(@PropertyBasedJsonCreator)
    )
    public static class Asset {

        /**
         * The id of the release, for GitHub bookkeeping.
         */
        Integer id;

        /**
         * The (API) URL to view this asset.
         */
        String url;

        /**
         * The URL where the asset can be downloaded.
         */
        String browserDownloadUrl;

        /**
         * THe name of this asset.
         * This is typically the file name.
         */
        String name;

        /**
         * A short description of this asset.
         */
        String label;

        /**
         * The content type of this asset.
         */
        MediaType contentType;

        /**
         * The state of the asset.
         */
        String state;

        /**
         * The size of this asset.
         */
        Long size;

        /**
         * The amount of downloads this asset has seen.
         */
        Long downloadCount;

        /**
         * The user who uploaded the asset.
         */
        User uploader;
    }
}
