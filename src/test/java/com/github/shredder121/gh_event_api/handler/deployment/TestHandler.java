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
    public void accept(DeploymentPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(DeploymentPayload::getDeployment, deploymentMatchers()),
                property(DeploymentPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(DeploymentPayload::getOrganization, is(nullValue())),
                property(DeploymentPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    public Matcher<Deployment> deploymentMatchers() {
        return allOf(asList(
                property(Deployment::getId, is(710692L)),
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
