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
package com.github.shredder121.gh_event_api.handler.push;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.Organization;
import com.github.shredder121.gh_event_api.model.PushCommit;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;
import com.google.common.collect.ImmutableList;

/**
 * The payload passed when a {@code push} event is received.
 *
 * @author Shredder121
 * @see <a href="https://developer.github.com/v3/activity/events/types/#pushevent">The push event on GitHub</a>
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class PushPayload {

    /**
     * The name of the branch that is pushed.
     */
    @NotNull String ref;

    /**
     * The SHA hash before the push.
     */
    @NotNull String before;

    /**
     * The SHA hash after the push.
     */
    @NotNull String after;

    /**
     * Whether the push created the {@link #ref ref}.
     */
    @NotNull Boolean created;

    /**
     * Whether the push deleted the {@link #ref ref}.
     */
    @NotNull Boolean deleted;

    /**
     * Whether the push was a force push.
     */
    @NotNull Boolean forced;

    /**
     * The commits being pushed.
     */
    @NotNull ImmutableList<PushCommit> commits;

    /**
     * The first commit in this push.
     */
    PushCommit headCommit;

    /**
     * The repository that was pushed to.
     */
    @NotNull Repository repository;

    /**
     * The organization the repository belongs to.
     * Only non-null with organization webhooks.
     */
    Organization organization;

    /**
     * The user who triggered the event.
     */
    @NotNull User sender;
}
