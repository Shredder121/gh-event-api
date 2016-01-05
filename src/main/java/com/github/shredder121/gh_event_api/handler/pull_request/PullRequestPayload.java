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

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.NonFinal;

/**
 * The payload passed when a {@code pull_request} event is received.
 *
 * @author Shredder121
 */
@lombok.Value
public class PullRequestPayload {

    /**
     * The Pull Request action that took place.
     */
    @NotNull String action;

    /**
     * The Pull Request number.
     */
    @NotNull Integer number;

    /**
     * The Pull Request that was affected.
     */
    @NotNull PullRequest pullRequest;

    /**
     * The Repository the Pull Request belongs to.
     */
    @NotNull Repository repository;

    /**
     * The User that invoked the action.
     */
    @NotNull User sender;

    //contextual properties

    /**
     * If the {@link #action action} of the event is
     * {@code labeled}
     * or
     * {@code unlabeled}
     * the label is included.
     *
     * @return the label if included, else {@code null}.
     */
    @NonFinal
    @Setter(AccessLevel.PRIVATE)
    Label label;

    /**
     * If the {@link #action action} of the event is
     * {@code assigned}
     * or
     * {@code unassigned}
     * the user is included.
     *
     * @return the user if included, else {@code null}.
     */
    @NonFinal
    @Setter(AccessLevel.PRIVATE)
    User user;

    @JsonCreator
    PullRequestPayload(
            @JsonProperty("action") String action,
            @JsonProperty("number") Integer number,
            @JsonProperty("pull_request") PullRequest pullRequest,
            @JsonProperty("repository") Repository repository,
            @JsonProperty("sender") User sender) {

        this.action = action;
        this.number = number;
        this.pullRequest = pullRequest;
        this.repository = repository;
        this.sender = sender;
    }
}
