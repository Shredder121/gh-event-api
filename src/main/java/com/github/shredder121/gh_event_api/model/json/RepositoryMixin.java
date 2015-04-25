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
package com.github.shredder121.gh_event_api.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Repository;

/**
 * Mirror object for {@link Repository}.
 *
 * @author Shredder121
 */
class RepositoryMixin {

    /**
     * {@link #RepositoryMixin(java.lang.String, java.lang.String)}
     * is a mirrored constructor for
     * {@link Repository#Repository(java.lang.String, java.lang.String)}.
     *
     * @param name the name to inject
     * @param full_name the full name to inject
     */
    @JsonCreator
    public RepositoryMixin(
            @JsonProperty("name") String name,
            @JsonProperty("full_name") String full_name) {
    }
}
