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

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * A deployment status is a status that is associated with a deployment.
 * This can be used to keep end users updated about the progress of the deployment.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class DeploymentStatus {

    /**
     * The id of the deployment status, for GitHub bookkeeping.
     */
    Long id;

    /**
     * The (API) URL to view this deployment status.
     */
    String url;

    /**
     * The (API) URL to view the deployment this status is associated with.
     */
    String deploymentUrl;

    /**
     * The (API) URL to view the repository for the deployment.
     */
    String repositoryUrl;

    /**
     * The state of the status.
     */
    String state;

    /**
     * The URL with more information/context regarding the deployment status.
     */
    String targetUrl;

    /**
     * The user who created this deployment status.
     */
    User creator;

    /**
     * A short description of the status.
     */
    String description;

    /**
     * The time the status was created.
     */
    ZonedDateTime createdAt;

    /**
     * The time the status was last updated.
     */
    ZonedDateTime updatedAt;
}
