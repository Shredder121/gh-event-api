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
import com.google.common.collect.ImmutableMap;

/**
 * A deployment is a request to deploy the output of a build.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.PROTECTED,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class Deployment {

    /**
     * The id for this deployment, for GitHub bookkeeping.
     */
    Integer id;

    /**
     * The (API) URL of this deployment.
     */
    String url;

    /**
     * The (API) URL to view this deployment's statuses.
     */
    String statusesUrl;

    /**
     * The (API) URL to view the repository this deployment is for.
     */
    String repositoryUrl;

    /**
     * The ref to deploy.
     * This can be a branch, tag or SHA.
     */
    String ref;

    /**
     * The SHA hash of the commit to deploy.
     */
    String sha;

    /**
     * The task to execute.
     */
    String task;

    /**
     * Optional payload with extra information about the deployment.
     */
    ImmutableMap<String, Object> payload;

    /**
     * The environment to deploy to.
     */
    String environment;

    /**
     * Optional short description.
     */
    String description;

    /**
     * The user that created this deployment.
     */
    User creator;

    /**
     * The time this deployment was created.
     */
    ZonedDateTime createdAt;

    /**
     * The time this deployment was last updated.
     */
    ZonedDateTime updatedAt;
}
