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
package com.github.shredder121.gh_event_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A repository is the location where code is hosted.
 */
@lombok.Value
public class Repository {

    /**
     * The name of the repository.
     */
    String name;

    /**
     * The full name of the repository.
     */
    String fullName;

    /**
     * The owner of the repository
     */
    User owner;

    /**
     * How many times this repository has been forked.
     */
    Long forksCount;

    /**
     * How many forks there are currently.
     */
    Long forks;

    @JsonCreator
    Repository(
            @JsonProperty("name") String name,
            @JsonProperty("full_name") String fullName,
            @JsonProperty("owner") User owner,
            @JsonProperty("forks_count") Long forksCount,
            @JsonProperty("forks") Long forks) {

        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.forksCount = forksCount;
        this.forks = forks;
    }
}
