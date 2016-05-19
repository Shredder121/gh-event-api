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

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * A GitCommit is an underlying commit object, with git metadata.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(SnakeCaseStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class GitCommit {

    /**
     * The person who authored the commit.
     */
    UserData author;

    /**
     * The person who applied the commit.
     */
    UserData committer;

    /**
     * The commit's message.
     */
    String message;

    /**
     * Details on author/committer data.
     */
    @lombok.Value
    @JsonNaming(SnakeCaseStrategy.class)
    @lombok.RequiredArgsConstructor(
            access = lombok.AccessLevel.MODULE,
            onConstructor = @__(@PropertyBasedJsonCreator)
    )
    public static class UserData {

        /**
         * The name as it shows up in the git log.
         */
        String name;

        /**
         * The email address the user uses.
         */
        String email;

        /**
         * The date of the commit.
         */
        ZonedDateTime date;
    }
}
