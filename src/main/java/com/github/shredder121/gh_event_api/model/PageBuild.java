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

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * A PageBuild is an attempted build of a GitHub Pages site.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.AllArgsConstructor(
        access = lombok.AccessLevel.PACKAGE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class PageBuild {

    /**
     * The URL to view this page build.
     */
    String url;

    /**
     * The commit that was built.
     */
    String commit;

    /**
     * The status of the build.
     */
    String status;

    /**
     * How many milliseconds elapsed.
     */
    Long duration;

    /**
     * Errors about the build.
     */
    BuildError error;

    /**
     * The user that triggered the build.
     */
    User pusher;

    /**
     * The creation date of this page build.
     */
    ZonedDateTime createdAt;

    /**
     * The time this page build was last updated.
     * (e.g. completed).
     */
    ZonedDateTime updatedAt;

    /**
     * Errors about the build.
     */
    @lombok.Value
    @JsonNaming(LowerCaseWithUnderscoresStrategy.class)
    @lombok.AllArgsConstructor(
            access = lombok.AccessLevel.PACKAGE,
            onConstructor = @__(@PropertyBasedJsonCreator)
    )
    public static class BuildError {

        /**
         * The message of the error, can be null.
         */
        String message;
    }
}
