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
package com.github.shredder121.gh_event_api.model.json;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.CommitComment;

/**
 * Mirror object for {@link CommitComment}.
 *
 * @author Shredder121
 */
abstract class CommitCommentMixin {

    /**
     * {@link #CommitCommentMixin(
     * java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.time.ZonedDateTime, java.time.ZonedDateTime)}
     *
     * is a mirrored constructor for
     *
     * {@link CommitComment#CommitComment(
     * java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.time.ZonedDateTime, java.time.ZonedDateTime)}.
     */
    CommitCommentMixin(
            @JsonProperty("id") Integer id,
            @JsonProperty("position") Integer position,
            @JsonProperty("path") String path,
            @JsonProperty("commit_id") String commitId,
            @JsonProperty("body") String body,
            @JsonProperty("url") String url,
            @JsonProperty("html_url") String htmlUrl,
            @JsonProperty("created_at") ZonedDateTime createdAt,
            @JsonProperty("updated_at") ZonedDateTime updatedAt) {
    }
}
