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
package com.github.shredder121.gh_event_api.handler.pull_request_review_comment;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.PullRequest;

/**
 * The payload passed when a {@code pull_request_review_comment} event is received.
 *
 * @author Shredder121
 */
@lombok.Value
public class PullRequestReviewCommentPayload {

    /**
     * The action that was performed on the comment.
     * Currently, can only be "created".
     */
    @NotNull String action;

    /**
     * The pull request the comment belongs to.
     */
    @NotNull PullRequest pullRequest;

    /**
     * the comment itself.
     */
    @NotNull Map<String, Object> comment;

    @JsonCreator
    PullRequestReviewCommentPayload(
            @JsonProperty("action") String action,
            @JsonProperty("pull_request") PullRequest pullRequest,
            @JsonProperty("comment") Map<String, Object> comment) {

        this.action = action;
        this.pullRequest = pullRequest;
        this.comment = comment;
    }
}
