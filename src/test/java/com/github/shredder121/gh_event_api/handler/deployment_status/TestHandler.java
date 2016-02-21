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
package com.github.shredder121.gh_event_api.handler.deployment_status;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Deployment;
import com.github.shredder121.gh_event_api.model.DeploymentStatus;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

class TestHandler extends AbstractTestHandlerBean implements DeploymentStatusHandler {

    @Override
    public void handle(DeploymentStatusPayload payload) {

        errorCollector.checkThat(payload.getDeploymentStatus(), allOf(Arrays.asList(
                property(DeploymentStatus::getId, is(1115122)),
                property(DeploymentStatus::getUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/deployments/710692/statuses/1115122")),
                property(DeploymentStatus::getDeploymentUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/deployments/710692")),
                property(DeploymentStatus::getRepositoryUrl, is("https://api.github.com/repos/baxterthehacker/public-repo")),
                property(DeploymentStatus::getState, is("success")),
                property(DeploymentStatus::getTargetUrl, is(nullValue())),
                property(DeploymentStatus::getCreator, allOf(
                        property(User::getId, is(6752317)),
                        property(User::getLogin, is("baxterthehacker")),
                        property(User::getHtmlUrl, is("https://github.com/baxterthehacker"))
                )),
                property(DeploymentStatus::getDescription, is(nullValue())),
                property(DeploymentStatus::getCreatedAt, hasToString(startsWith("2015-05-05T23:40:39Z"))),
                property(DeploymentStatus::getUpdatedAt, hasToString(startsWith("2015-05-05T23:40:39Z")))
        )));

        errorCollector.checkThat(payload.getDeployment(), allOf(
                property(Deployment::getId, is(710692)),
                property(Deployment::getSha, is("9049f1265b7d61be4a8904a9a27120d2064dab3b"))
        ));

        errorCollector.checkThat(payload.getRepository(), allOf(
                property(Repository::getName, is("public-repo")),
                property(Repository::getFullName, is("baxterthehacker/public-repo"))
        ));

        errorCollector.checkThat(payload.getOrganization(), is(nullValue()));

        errorCollector.checkThat(payload.getSender(), allOf(
                property(User::getId, is(6752317)),
                property(User::getLogin, is("baxterthehacker")),
                property(User::getHtmlUrl, is("https://github.com/baxterthehacker"))
        ));

        countDownLatch.countDown();
    }
}
