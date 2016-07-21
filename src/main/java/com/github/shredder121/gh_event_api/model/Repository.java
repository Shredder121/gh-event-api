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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * A repository is the location where code is hosted.
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.PROTECTED,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class Repository {

    /**
     * The id of this repository.
     */
    Integer id;

    /**
     * The name of the repository.
     */
    String name;

    /**
     * The full name of the repository.
     */
    String fullName;

    /**
     * The description of the repository.
     */
    String description;

    /**
     * The default branch of the repository.
     */
    String defaultBranch;

    /**
     * The homepage of the repository.
     */
    String homepage;

    /**
     * The most prevalent language that
     */
    String language;

    /**
     * The owner of the repository
     */
    User owner;

    /**
     * How many times this repository has been forked.
     */
    Long forksCount;

    /**
     * How many forks there are currently.
     */
    Long forks;

    /**
     * Whether this repository is a fork of another repository.
     */
    @JsonProperty("fork")
    boolean isFork;

    /**
     * Whether this repository is a private repository.
     */
    @JsonProperty("private")
    boolean isPrivate;

    /**
     * The (API) URL to view this repository.
     */
    String url;

    /**
     * The (GitHub Web UI) URL to view this repository.
     */
    String htmlUrl;

    /**
     * The time this repository was created.
     */
    ZonedDateTime createdAt;

    /**
     * The time this repository was last updated.
     */
    ZonedDateTime updatedAt;

    /**
     * The time the repository was last pushed to.
     */
    ZonedDateTime pushedAt;
}
