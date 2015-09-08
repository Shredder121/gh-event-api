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

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Commit;
import com.github.shredder121.gh_event_api.model.GitCommit;
import com.github.shredder121.gh_event_api.model.User;

/**
 * Mirror object for {@link Commit}.
 *
 * @author Shredder121
 */
class CommitMixin {

    /**
     * {@link #CommitMixin(java.lang.String, com.github.shredder121.gh_event_api.model.GitCommit, java.lang.String, java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.User, com.github.shredder121.gh_event_api.model.User, java.util.Collection)}
     * is a mirrored constructor for
     * {@link Commit#Commit(java.lang.String, com.github.shredder121.gh_event_api.model.GitCommit, java.lang.String, java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.User, com.github.shredder121.gh_event_api.model.User, java.util.Collection)}.
     *
     * @param sha the sha to inject
     * @param commit the git commit to inject
     * @param url the url to inject
     * @param html_url the HTML url to inject
     * @param comments_url the comments url to inject
     * @param author the author to inject
     * @param committer the commiter to inject
     * @param parents the parent commits to inject
     */
    @JsonCreator
    public CommitMixin(
            @JsonProperty("sha") String sha,
            @JsonProperty("commit") GitCommit commit,
            @JsonProperty("url") String url,
            @JsonProperty("html_url") String html_url,
            @JsonProperty("comments_url") String comments_url,
            @JsonProperty("commiter") User author, User committer,
            @JsonProperty("parents") Collection<Commit> parents) {
    }
}
