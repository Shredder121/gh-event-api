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
package com.github.shredder121.gh_event_api.handler.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The payload passed when a {@code create} event is received.
 *
 * @author Shredder121
 */
@lombok.Value
public class CreatePayload {

    /**
     * The type of the object being created.
     * Can be one of 'repository', 'branch' or 'tag'.
     */
    String refType;

    /**
     * The git ref (or null if only a repository was created).
     */
    String ref;

    /**
     * The name of the repository's default branch.
     */
    String masterBranch;

    /**
     * The repository's current description.
     */
    String description;

    @JsonCreator
    CreatePayload(
            @JsonProperty("ref_type") String refType,
            @JsonProperty("ref") String ref,
            @JsonProperty("master_branch") String masterBranch,
            @JsonProperty("description") String description) {

        this.refType = refType;
        this.ref = ref;
        this.masterBranch = masterBranch;
        this.description = description;
    }
}
