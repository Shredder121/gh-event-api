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

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
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
}
