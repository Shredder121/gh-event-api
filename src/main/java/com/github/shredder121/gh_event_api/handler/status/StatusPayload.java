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
package com.github.shredder121.gh_event_api.handler.status;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Branch;
import com.github.shredder121.gh_event_api.model.Repository;

public class StatusPayload {

    @NotNull private final String sha;
    @NotNull private final String name;
    @NotNull private final String context;
    @NotNull private final String state;
    private final String description;
    private final String target_url;
    @NotNull private final Collection<Branch> branches;
    @NotNull private final Repository repository;

    @JsonCreator
    public StatusPayload(
            @JsonProperty("sha") String sha,
            @JsonProperty("name") String name,
            @JsonProperty("context") String context,
            @JsonProperty("state") String state,
            @JsonProperty("description") String description,
            @JsonProperty("target_url") String target_url,
            @JsonProperty("branches") Collection<Branch> branches,
            @JsonProperty("repository") Repository repository) {

        this.sha = sha;
        this.name = name;
        this.context = context;
        this.state = state;
        this.description = description;
        this.target_url = target_url;
        this.branches = Collections.unmodifiableCollection(branches);
        this.repository = repository;
    }

    public String getSha() {
        return sha;
    }

    public String getName() {
        return name;
    }

    public String getContext() {
        return context;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public String getTarget_url() {
        return target_url;
    }

    public Collection<Branch> getBranches() {
        return branches;
    }

    public Repository getRepository() {
        return repository;
    }
}
