package com.github.shredder121.gh_event_api.handler.deployment_status;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Deployment;
import com.github.shredder121.gh_event_api.model.DeploymentStatus;

class TestHandler extends AbstractTestHandlerBean implements DeploymentStatusHandler {

    @Override
    public void accept(DeploymentStatusPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(DeploymentStatusPayload::getDeploymentStatus, deploymentStatusMatchers()),
                property(DeploymentStatusPayload::getDeployment, deploymentMatchers()),
                property(DeploymentStatusPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(DeploymentStatusPayload::getOrganization, is(nullValue())),
                property(DeploymentStatusPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    public Matcher<DeploymentStatus> deploymentStatusMatchers() {
        return allOf(asList(
                property(DeploymentStatus::getId, is(1115122L)),
                property(DeploymentStatus::getUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/deployments/710692/statuses/1115122")),
                property(DeploymentStatus::getDeploymentUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/deployments/710692")),
                property(DeploymentStatus::getRepositoryUrl, is("https://api.github.com/repos/baxterthehacker/public-repo")),
                property(DeploymentStatus::getState, is("success")),
                property(DeploymentStatus::getTargetUrl, is(nullValue())),
                property(DeploymentStatus::getCreator, is(BAXTERTHEHACKER)),
                property(DeploymentStatus::getDescription, is(nullValue())),
                property(DeploymentStatus::getCreatedAt, hasToString(startsWith("2015-05-05T23:40:39Z"))),
                property(DeploymentStatus::getUpdatedAt, hasToString(startsWith("2015-05-05T23:40:39Z")))
        ));
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
