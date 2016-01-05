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
 * A ref is a GitHub pointer to a commit.
 *
 * @author Shredder121
 */
@lombok.Value
public class Ref {

    /**
     * The {owner:repo} label of the ref.
     */
    String label;

    /**
     * The (git) name of the ref.
     */
    String ref;

    /**
     * The SHA hash of the commit.
     */
    String sha;

    /**
     * The user of the repo.
     */
    User user;

    /**
     * The repository the commit resides.
     */
    Repository repo;

    @JsonCreator
    Ref(
            @JsonProperty("label") String label,
            @JsonProperty("ref") String ref,
            @JsonProperty("sha") String sha,
            @JsonProperty("user") User user,
            @JsonProperty("repo") Repository repo) {

        this.label = label;
        this.ref = ref;
        this.sha = sha;
        this.user = user;
        this.repo = repo;
    }
}
