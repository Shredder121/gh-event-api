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

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

/**
 *
 * @author Shredder121
 */
public class DeploymentStatusSerializationTest extends AbstractSerializationTest<DeploymentStatus> {

    private static final String SAMPLE_DEPLOYMENT_STATUS
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/deployments/3640119/statuses/6811556\",\n"
            + "  \"id\": 6811556,\n"
            + "  \"state\": \"success\",\n"
            + "  \"creator\": {\n"
            + "    \"login\": \"Shredder121\",\n"
            + "    \"id\": 4105066,\n"
            + "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/4105066?v=3\",\n"
            + "    \"gravatar_id\": \"\",\n"
            + "    \"url\": \"https://api.github.com/users/Shredder121\",\n"
            + "    \"html_url\": \"https://github.com/Shredder121\",\n"
            + "    \"followers_url\": \"https://api.github.com/users/Shredder121/followers\",\n"
            + "    \"following_url\": \"https://api.github.com/users/Shredder121/following{/other_user}\",\n"
            + "    \"gists_url\": \"https://api.github.com/users/Shredder121/gists{/gist_id}\",\n"
            + "    \"starred_url\": \"https://api.github.com/users/Shredder121/starred{/owner}{/repo}\",\n"
            + "    \"subscriptions_url\": \"https://api.github.com/users/Shredder121/subscriptions\",\n"
            + "    \"organizations_url\": \"https://api.github.com/users/Shredder121/orgs\",\n"
            + "    \"repos_url\": \"https://api.github.com/users/Shredder121/repos\",\n"
            + "    \"events_url\": \"https://api.github.com/users/Shredder121/events{/privacy}\",\n"
            + "    \"received_events_url\": \"https://api.github.com/users/Shredder121/received_events\",\n"
            + "    \"type\": \"User\",\n"
            + "    \"site_admin\": false\n"
            + "  },\n"
            + "  \"description\": \"finally got around to deployment statuses\",\n"
            + "  \"target_url\": null,\n"
            + "  \"created_at\": \"2016-02-16T08:29:45Z\",\n"
            + "  \"updated_at\": \"2016-02-16T08:29:45Z\",\n"
            + "  \"deployment_url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/deployments/3640119\",\n"
            + "  \"repository_url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle\"\n"
            + "}";

    public DeploymentStatusSerializationTest() {
        super(DeploymentStatus.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_DEPLOYMENT_STATUS;
    }

    @Override
    protected void checkDeserialized(DeploymentStatus deploymentStatus) {
        errorCollector.checkThat(deploymentStatus, allOf(Arrays.asList(
                property(DeploymentStatus::getId, is(6811556)),
                property(DeploymentStatus::getUrl, is("https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/deployments/3640119/statuses/6811556")),
                property(DeploymentStatus::getState, is("success")),
                property(DeploymentStatus::getCreator, allOf(
                        property(User::getId, is(4105066)),
                        property(User::getLogin, is("Shredder121")),
                        property(User::getHtmlUrl, is("https://github.com/Shredder121"))
                )),
                property(DeploymentStatus::getDescription, is("finally got around to deployment statuses")),
                property(DeploymentStatus::getTargetUrl, is(nullValue())),
                property(DeploymentStatus::getCreatedAt, hasToString(startsWith("2016-02-16T08:29:45Z"))),
                property(DeploymentStatus::getUpdatedAt, hasToString(startsWith("2016-02-16T08:29:45Z"))),
                property(DeploymentStatus::getDeploymentUrl, is("https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/deployments/3640119")),
                property(DeploymentStatus::getRepositoryUrl, is("https://api.github.com/repos/Shredder121-me/yummy-octo-turtle"))
        )));
    }
}
