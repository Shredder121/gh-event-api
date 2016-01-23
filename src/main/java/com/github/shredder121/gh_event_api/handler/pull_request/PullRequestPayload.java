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
package com.github.shredder121.gh_event_api.handler.pull_request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.Label;
import com.github.shredder121.gh_event_api.model.PullRequest;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * The payload passed when a {@code pull_request} event is received.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.AllArgsConstructor(
        access = lombok.AccessLevel.PACKAGE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
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

    /**
     * If the {@link #action action} of the event is
     * {@code labeled}
     * or
     * {@code unlabeled}
     * the label is included.
     *
     * @return the label if included, else {@code null}.
     */
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
    User user;
}
