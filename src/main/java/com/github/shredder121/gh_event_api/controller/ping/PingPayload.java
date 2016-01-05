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
package com.github.shredder121.gh_event_api.controller.ping;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The payload passed when a {@code ping} event is received.
 *
 * <p>Only for internal use.</p>
 *
 * @author Shredder121
 */
@lombok.Value
public class PingPayload {

    /**
     * A random piece of GitHub zen.
     * Will be echoed back to GitHub to indicate successful configuration.
     */
    @NotNull String zen;

    @JsonCreator
    PingPayload(
            @JsonProperty("zen") String zen) {

        this.zen = zen;
    }
}
