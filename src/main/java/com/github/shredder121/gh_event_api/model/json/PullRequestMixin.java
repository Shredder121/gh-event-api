/*
 * Copyright 2015 Shredder121.
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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Link;
import com.github.shredder121.gh_event_api.model.PullRequest;
import com.github.shredder121.gh_event_api.model.Ref;
import com.google.common.collect.ImmutableMap;

/**
 * Mirror object for {@link PullRequest}.
 *
 * @author Shredder121
 */
abstract class PullRequestMixin {

    /**
     * {@link #PullRequestMixin(
     * java.lang.String, java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.String, java.time.ZonedDateTime, com.github.shredder121.gh_event_api.model.Ref, com.google.common.collect.ImmutableMap)}
     * is a mirrored constructor for
     * {@link PullRequest#PullRequest(
     * java.lang.String, java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.String, java.time.ZonedDateTime, com.github.shredder121.gh_event_api.model.Ref, com.google.common.collect.ImmutableMap)}.
     *
     * @param url the url of the pull request
     * @param number the pull request number
     * @param state the pull request state
     * @param locked whether the pull request is locked
     * @param title the pull request title
     * @param createdAt the pull request's creation date
     * @param head the tip of the pull request's branch
     * @param links navigational links
     */
    @JsonCreator
    PullRequestMixin(
            @JsonProperty("url") String url,
            @JsonProperty("number") Integer number,
            @JsonProperty("state") String state,
            @JsonProperty("locked") Boolean locked,
            @JsonProperty("title") String title,
            @JsonProperty("created_at") ZonedDateTime createdAt,
            @JsonProperty("head") Ref head,
            @JsonProperty("_links") ImmutableMap<String, Link> links) {
    }
}
