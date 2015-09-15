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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Branch;
import com.github.shredder121.gh_event_api.model.GitCommit;

/**
 *
 * @author Shredder121
 */
public class BranchMixin {

    /**
     * {@link #BranchMixin(java.lang.String, com.github.shredder121.gh_event_api.model.GitCommit)}
     * is a mirrored constructor for
     * {@link Branch#Branch(java.lang.String, com.github.shredder121.gh_event_api.model.GitCommit)}
     *
     * @param name the name to inject
     * @param commit the commit object to inject
     */
    public BranchMixin(
            @JsonProperty("name") String name,
            @JsonProperty("commit") GitCommit commit) {
    }
}