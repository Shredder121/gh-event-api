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
public class CreatePayload {

    private final String ref_type;
    private final String ref;
    private final String master_branch;
    private final String description;

    @JsonCreator
    public CreatePayload(
            @JsonProperty("ref_type") String ref_type,
            @JsonProperty("ref") String ref,
            @JsonProperty("master_branch") String master_branch,
            @JsonProperty("description") String description) {

        this.ref_type = ref_type;
        this.ref = ref;
        this.master_branch = master_branch;
        this.description = description;
    }

    public String getRef_type() {
        return ref_type;
    }

    public String getRef() {
        return ref;
    }

    public String getMaster_branch() {
        return master_branch;
    }

    public String getDescription() {
        return description;
    }
}
