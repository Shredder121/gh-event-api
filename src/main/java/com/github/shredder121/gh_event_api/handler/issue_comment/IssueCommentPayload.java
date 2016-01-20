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
package com.github.shredder121.gh_event_api.handler.issue_comment;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Comment;
import com.github.shredder121.gh_event_api.model.Issue;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

/**
 * The payload passed when a {@code issue_comment} event is received.
 *
 * @author Shredder121
 */
@lombok.Value
public class IssueCommentPayload {

    /**
     * The action that was performed on the comment.
     * Currently, can only be {@code created}
     */
    @NotNull String action;

    /**
     * The issue the comment belongs to.
     */
    @NotNull Issue issue;

    /**
     * The comment itself.
     */
    @NotNull Comment comment;

    /**
     * The repository the issue belongs to.
     */
    @NotNull Repository repository;

    /**
     * The user commenting on the issue.
     */
    @NotNull User sender;

    @JsonCreator
    IssueCommentPayload(
            @JsonProperty("action") String action,
            @JsonProperty("issue") Issue issue,
            @JsonProperty("comment") Comment comment,
            @JsonProperty("repository") Repository repository,
            @JsonProperty("sender") User sender) {

        this.action = action;
        this.issue = issue;
        this.comment = comment;
        this.repository = repository;
        this.sender = sender;
    }
}
