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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

/**
 * An issue is a request for an enhancement on GitHub.
 *
 * @author Shredder121
 */
@lombok.Value
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

    @JsonCreator
    Issue(
            @JsonProperty("id") Integer id,
            @JsonProperty("number") Integer number,
            @JsonProperty("state") String state,
            @JsonProperty("locked") Boolean locked,
            @JsonProperty("title") String title,
            @JsonProperty("body") String body,
            @JsonProperty("user") User user,
            @JsonProperty("labels") ImmutableList<Label> labels,
            @JsonProperty("created_at") ZonedDateTime createdAt,
            @JsonProperty("updated_at") ZonedDateTime updatedAt) {

        this.id = id;
        this.number = number;
        this.state = state;
        this.locked = locked;
        this.title = title;
        this.body = body;
        this.user = user;
        this.labels = labels;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
