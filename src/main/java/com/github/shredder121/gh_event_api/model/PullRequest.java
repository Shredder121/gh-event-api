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
import com.google.common.collect.ImmutableMap;

/**
 * A pull request is a way of suggesting to merge back changes.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.AllArgsConstructor(
        access = lombok.AccessLevel.PACKAGE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class PullRequest {

    /**
     * The (API) URL to fetch this Pull Request.
     */
    String url;

    /**
     * The pull request number.
     */
    Integer number;

    /**
     * The state of the Pull Request.
     */
    String state;

    /**
     * Whether the Pull Request has been locked to collaborators only.
     */
    Boolean locked;

    /**
     * The Pull Request title.
     */
    String title;

    /**
     * The creation date of the pull request.
     */
    ZonedDateTime createdAt;

    /**
     * A pointer to the head commit.
     */
    Ref head;

    /**
     * Additional links included in the Pull Request.
     */
    @JsonProperty("_links")
    ImmutableMap<String, Link> links;
}
