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
import com.github.shredder121.gh_event_api.model.GitCommit;
import com.github.shredder121.gh_event_api.model.Repository;

/**
 * Mirror object for {@link GitCommit}.
 *
 * @author Shredder121
 */
class GitCommitMixin {

    /**
     * {@link #GitCommitMixin(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.Repository)}
     * is a mirrored constructor for
     * {@link GitCommit#GitCommit(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.Repository)}.
     *
     * @param label the label
     * @param ref the ref of the label
     * @param sha the sha hash of the ref
     * @param message the message of the commit
     * @param repo the repository of the commit
     */
    @JsonCreator
    public GitCommitMixin(
            @JsonProperty("label") String label,
            @JsonProperty("ref") String ref,
            @JsonProperty("sha") String sha,
            @JsonProperty("message") String message,
            @JsonProperty("repo") Repository repo) {
    }
}
