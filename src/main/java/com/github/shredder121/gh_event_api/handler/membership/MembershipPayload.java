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
package com.github.shredder121.gh_event_api.handler.membership;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.Team;
import com.github.shredder121.gh_event_api.model.User;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * The payload passed when a {@code membership} event is received.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.AllArgsConstructor(
        access = lombok.AccessLevel.PACKAGE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class MembershipPayload {

    /**
     * The action that was performed.
     * Can be 'added' or 'removed'.
     */
    @NotNull String action;

    /**
     * The scope of the membership.
     * Currently, can only be 'team'.
     */
    @NotNull String scope;

    /**
     * The user that was added or removed.
     */
    @NotNull User member;

    /**
     * The team for the membership.
     */
    @NotNull Team team;

    /**
     * The person that triggered the event.
     */
    @NotNull User sender;
}