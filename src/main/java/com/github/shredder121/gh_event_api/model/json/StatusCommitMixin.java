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
import com.github.shredder121.gh_event_api.model.GitCommit;
import com.github.shredder121.gh_event_api.model.StatusCommit;

/**
 * Mirror object for {@link StatusCommit}.
 *
 * @author Shredder121
 */
abstract class StatusCommitMixin {

    /**
     * {@link #StatusCommitMixin(
     * java.lang.String, java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.GitCommit)}
     *
     * is a mirrored constructor for
     *
     * {@link StatusCommit#StatusCommit(
     * java.lang.String, java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.GitCommit)}.
     *
     * @param sha
     * @param url
     * @param htmlUrl
     * @param commit
     */
    @JsonCreator
    StatusCommitMixin(
            @JsonProperty("sha") String sha,
            @JsonProperty("url") String url,
            @JsonProperty("html_url") String htmlUrl,
            @JsonProperty("commit") GitCommit commit) {
    }
}
