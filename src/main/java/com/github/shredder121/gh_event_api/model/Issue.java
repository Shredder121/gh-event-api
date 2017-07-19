/*
 * Copyright 2017 Shredder121.
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

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;
import com.google.common.collect.ImmutableList;

/**
 * An issue is a request for an enhancement on GitHub.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(SnakeCaseStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class Issue {

    /**
     * Unique id given by GitHub.
     */
    Integer id;

    /**
     * The issue number.
     */
    Integer number;

    /**
     * The (API) URL to view this issue.
     */
    String url;

    /**
     * The (GitHub Web UI) URL to view this issue.
     */
    String htmlUrl;

    /**
     * The issue state.
     *
     * e.g. open, closed
     */
    String state;

    /**
     * Whether the issue has been locked.
     */
    Boolean locked;

    /**
     * The issue title.
     */
    String title;

    /**
     * The issue body.
     */
    String body;

    /**
     * The creator of the issue.
     */
    User user;

    /**
     * The labels of the issue.
     */
    ImmutableList<Label> labels;

    /**
     * The moment the issue was created.
     */
    ZonedDateTime createdAt;

    /**
     * The moment the issue was last updated.
     */
    ZonedDateTime updatedAt;
}
