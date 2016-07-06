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

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * A repository is the location where code is hosted.
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.PROTECTED,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class Repository {

    /**
     * The name of the repository.
     */
    String name;

    /**
     * The full name of the repository.
     */
    String fullName;

    /**
     * The owner of the repository
     */
    User owner;

    /**
     * How many times this repository has been forked.
     */
    Long forksCount;

    /**
     * How many forks there are currently.
     */
    Long forks;
}
