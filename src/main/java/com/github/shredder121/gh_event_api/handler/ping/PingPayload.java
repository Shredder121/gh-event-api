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
package com.github.shredder121.gh_event_api.handler.ping;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;
import com.google.common.collect.ImmutableMap;

/**
 * The payload passed when a {@code ping} event is received.
 *
 * <p>Only for internal use.</p>
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.AllArgsConstructor(
        access = lombok.AccessLevel.PACKAGE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class PingPayload {

    /**
     * A random piece of GitHub zen.
     * Will be echoed back to GitHub to indicate successful configuration.
     */
    @NotNull String zen;

    /**
     * The id of the newly created hook.
     */
    @NotNull Integer hookId;

    /**
     * The hook's configuration.
     */
    @NotNull ImmutableMap<String, Object> hook;

    /**
     * The repository that the hook belongs to.
     */
    @NotNull Repository repository;

    /**
     * The user that created the hook.
     */
    @NotNull User sender;
}
