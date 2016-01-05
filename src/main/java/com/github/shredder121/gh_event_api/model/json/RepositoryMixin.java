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
package com.github.shredder121.gh_event_api.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

/**
 * Mirror object for {@link Repository}.
 *
 * @author Shredder121
 */
abstract class RepositoryMixin {

    /**
     * {@link #RepositoryMixin(java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.User, java.lang.Long, java.lang.Long)}
     * is a mirrored constructor for
     * {@link Repository#Repository(java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.User, java.lang.Long, java.lang.Long)}.
     *
     * @param name the name to inject
     * @param fullName the full name to inject
     * @param owner the owner to inject
     * @param forksCount the forks_count to inject
     * @param forks the forks to inject
     */
    @JsonCreator
    RepositoryMixin(
            @JsonProperty("name") String name,
            @JsonProperty("full_name") String fullName,
            @JsonProperty("owner") User owner,
            @JsonProperty("forks_count") Long forksCount,
            @JsonProperty("forks") Long forks) {
    }
}
