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
package com.github.shredder121.gh_event_api.handler.status;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.StatusBranch;
import com.github.shredder121.gh_event_api.model.StatusCommit;
import com.google.common.collect.ImmutableList;

/**
 * The payload passed when a {@code status} event is received.
 *
 * @author Shredder121
 */
@lombok.Value
public class StatusPayload {

    /**
     * The Commit SHA.
     */
    @NotNull String sha;

    /**
     * The name of the repository.
     */
    @NotNull String name;

    /**
     * The context of the status.
     */
    @NotNull String context;

    /**
     * The new state.
     */
    @NotNull String state;

    /**
     * The optional human-readable description added to the status.
     */
    String description;

    /**
     * The optional link added to the status.
     *
     */
    String targetUrl;

    /**
     * The commit that the status is for.
     */
    @NotNull StatusCommit commit;

    /**
     * A list of branch objects containing the commit.
     *
     * <p>Each branch contains the given commit, but the commit may or may not be the head of the branch.</p>
     *
     * <p>The list includes a maximum of 10 branches.</p>
     */
    @NotNull ImmutableList<StatusBranch> branches;

    /**
     * The repository of the status.
     */
    @NotNull Repository repository;

    @JsonCreator
    StatusPayload(
            @JsonProperty("sha") String sha,
            @JsonProperty("name") String name,
            @JsonProperty("context") String context,
            @JsonProperty("state") String state,
            @JsonProperty("description") String description,
            @JsonProperty("target_url") String targetUrl,
            @JsonProperty("commit") StatusCommit commit,
            @JsonProperty("branches") ImmutableList<StatusBranch> branches,
            @JsonProperty("repository") Repository repository) {

        this.sha = sha;
        this.name = name;
        this.context = context;
        this.state = state;
        this.description = description;
        this.targetUrl = targetUrl;
        this.commit = commit;
        this.branches = branches;
        this.repository = repository;
    }
}
