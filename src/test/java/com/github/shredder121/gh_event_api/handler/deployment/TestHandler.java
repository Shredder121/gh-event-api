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
package com.github.shredder121.gh_event_api.handler.deployment;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Deployment;

class TestHandler extends AbstractTestHandlerBean implements DeploymentHandler {

    @Override
    public void handle(DeploymentPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(DeploymentPayload::getDeployment, deploymentMatchers()),
                property(DeploymentPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(DeploymentPayload::getOrganization, is(nullValue())),
                property(DeploymentPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    public static Matcher<Deployment> deploymentMatchers() {
        return allOf(asList(
                property(Deployment::getId, is(710692)),
                property(Deployment::getRef, is("master")),
                property(Deployment::getSha, is("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(Deployment::getTask, is("deploy")),
                property(Deployment::getPayload, is(any(Map.class))),
                property(Deployment::getEnvironment, is("production")),
                property(Deployment::getDescription, is(nullValue())),
                property(Deployment::getCreator, is(BAXTERTHEHACKER)),
                property(Deployment::getCreatedAt, hasToString(startsWith("2015-05-05T23:40:38Z"))),
                property(Deployment::getUpdatedAt, hasToString(startsWith("2015-05-05T23:40:38Z")))
        ));
    }
}
