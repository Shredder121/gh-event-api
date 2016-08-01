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
package com.github.shredder121.gh_event_api.handler.repository;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.Organization;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * The payload passed when a {@code repository} event is received.
 *
 * @author Shredder121
 * @see <a href="https://developer.github.com/v3/activity/events/types/#repositoryevent">The repository event on GitHub</a>
 */
@lombok.Value
@JsonNaming(SnakeCaseStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class RepositoryPayload {

    /**
     * The action that was performed.
     * Currently, can only be 'created'.
     */
    @NotNull String action;

    /**
     * The repository that was created.
     */
    @NotNull Repository repository;

    /**
     * The organization the repository belongs to.
     */
    @NotNull Organization organization;

    /**
     * The user who triggered the request.
     */
    @NotNull User sender;
}
