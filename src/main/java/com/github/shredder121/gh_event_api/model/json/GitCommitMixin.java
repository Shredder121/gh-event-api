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

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.GitCommit;
import com.github.shredder121.gh_event_api.model.GitCommit.UserData;

/**
 * Mirror object for {@link GitCommit}.
 *
 * @author Shredder121
 */
abstract class GitCommitMixin {

    /**
     * {@link #GitCommitMixin(
     * com.github.shredder121.gh_event_api.model.GitCommit.UserData, com.github.shredder121.gh_event_api.model.GitCommit.UserData, java.lang.String)}
     *
     * is a mirrored constructor for
     *
     * {@link GitCommit#GitCommit(
     * com.github.shredder121.gh_event_api.model.GitCommit.UserData, com.github.shredder121.gh_event_api.model.GitCommit.UserData, java.lang.String)}.
     *
     * @param author the author to inject
     * @param committer the committer to inject
     * @param message the message to inject
     */
    @JsonCreator
    GitCommitMixin(
            @JsonProperty("author") UserData author,
            @JsonProperty("committer") UserData committer,
            @JsonProperty("message") String message) {
    }

    abstract static class UserDataMixin {

        /**
         * {@link #UserDataMixin(java.lang.String, java.lang.String, java.time.ZonedDateTime)}
         *
         * is a mirrored constructor for
         *
         * {@link UserData#UserData(java.lang.String, java.time.ZonedDateTime)}.
         *
         * @param name the name to inject
         * @param email the email to inject
         * @param date the date to inject
         */
        @JsonCreator
        UserDataMixin(
                @JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("date") ZonedDateTime date) {
        }
    }
}
