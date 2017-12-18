/*
 * Copyright 2017 Shredder121.
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
 * An organization is a group of users that operate repositories.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class Organization {

    /**
     * The username/handle of the organization.
     */
    String login;

    /**
     * The id of this organization.
     */
    Long id;

    /**
     * This organization's description.
     */
    String description;

    /**
     * The (API) URL to view this organization.
     */
    String url;

    /**
     * The (API) URL to view this organization's repositories.
     */
    String reposUrl;

    /**
     * The (API) URL to view the event time line of this organization.
     */
    String eventsUrl;

    /**
     * The (API) URL to view the hooks of this organization.
     */
    String hooksUrl;

    /**
     * The (API) URL to view this organization's issues.
     */
    String issuesUrl;

    /**
     * The (API) URL to view the members of this organization.
     */
    String membersUrl;

    /**
     * The (API) URL to view the public members of this organization.
     */
    String publicMembersUrl;

    /**
     * The avatar image of this organization.
     */
    String avatarUrl;
}
