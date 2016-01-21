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
package com.github.shredder121.gh_event_api.handler.issues;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Issue;
import com.github.shredder121.gh_event_api.model.Label;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

/**
 * The payload passed when an {@code issues} event is received.
 *
 * @author Shredder121
 */
@lombok.Value
public class IssuesPayload {

    /**
     * The action that was performed.
     */
    @NotNull String action;

    /**
     * The issue itself.
     */
    @NotNull Issue issue;

    /**
     * The repository the issue belongs to.
     */
    @NotNull Repository repository;

    /**
     * The user that created the issue.
     */
    @NotNull User sender;

    /**
     * The optional user who was assigned or unassigned from the issue.
     */
    User assignee;

    /**
     * The optional label that was added or removed from the issue.
     */
    Label label;

    @JsonCreator
    IssuesPayload(
            @JsonProperty("action") String action,
            @JsonProperty("issue") Issue issue,
            @JsonProperty("repository") Repository repository,
            @JsonProperty("sender") User sender,
            @JsonProperty("assignee") User assignee,
            @JsonProperty("label") Label label) {

        this.action = action;
        this.issue = issue;
        this.repository = repository;
        this.sender = sender;
        this.assignee = assignee;
        this.label = label;
    }
}
