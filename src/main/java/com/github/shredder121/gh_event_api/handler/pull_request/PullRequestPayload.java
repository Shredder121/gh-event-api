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
package com.github.shredder121.gh_event_api.handler.pull_request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Label;
import com.github.shredder121.gh_event_api.model.PullRequest;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

/**
 * The payload passed when a {@code pull_request} event is received.
 *
 * @author Shredder121
 */
public class PullRequestPayload {

    @NotNull private final String action;
    @NotNull private final Integer number;
    @NotNull private final PullRequest pull_request;
    @NotNull private final Repository repository;
    @NotNull private final User sender;

    //contextual properties
    private Label label;
    private User user;

    @JsonCreator
    public PullRequestPayload(
            @JsonProperty("action") String action,
            @JsonProperty("number") Integer number,
            @JsonProperty("pull_request") PullRequest pull_request,
            @JsonProperty("repository") Repository repository,
            @JsonProperty("sender") User sender) {

        this.action = action;
        this.number = number;
        this.pull_request = pull_request;
        this.repository = repository;
        this.sender = sender;
    }

    public String getAction() {
        return action;
    }

    public Integer getNumber() {
        return number;
    }

    public PullRequest getPullRequest() {
        return pull_request;
    }

    public Repository getRepository() {
        return repository;
    }

    public User getSender() {
        return sender;
    }

    /**
     * If the {@link #action action} of the event is
     * {@link PullRequestEvent#LABELED labeled}
     * or
     * {@link PullRequestEvent#UNLABELED unlabeled}
     * the label is included.
     *
     * @return the label if included, else {@code null}.
     */
    public Label getLabel() {
        return label;
    }

    private void setLabel(Label label) {
        this.label = label;
    }

    /**
     * If the {@link #action action} of the event is
     * {@link PullRequestEvent#ASSIGNED assigned}
     * or
     * {@link PullRequestEvent#UNASSIGNED unassigned}
     * the user is included.
     *
     * @return the user if included, else {@code null}.
     */
    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }
}
