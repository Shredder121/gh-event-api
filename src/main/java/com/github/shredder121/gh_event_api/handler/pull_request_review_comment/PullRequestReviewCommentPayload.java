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
package com.github.shredder121.gh_event_api.handler.pull_request_review_comment;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.*;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * The payload passed when a {@code pull_request_review_comment} event is received.
 *
 * @author Shredder121
 * @see <a href="https://developer.github.com/v3/activity/events/types/#pullrequestreviewcommentevent">The pull_request_review_comment event on GitHub</a>
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
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
    @NotNull Comment comment;

    /**
     * The repository of the pull request being commented on.
     */
    @NotNull Repository repository;

    /**
     * The organization the repository belongs to.
     * Only non-null with organization webhooks.
     */
    Organization organization;

    /**
     * The user that invoked the action.
     */
    @NotNull User sender;
}
