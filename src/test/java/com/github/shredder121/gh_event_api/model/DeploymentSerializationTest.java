package com.github.shredder121.gh_event_api.model;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Map;

public class DeploymentSerializationTest extends AbstractSerializationTest<Deployment> {

    static String SAMPLE_DEPLOYMENT
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/deployments/3640119\",\n"
            + "  \"id\": 3640119,\n"
            + "  \"sha\": \"bef42899aa30ad50dfcc4465e0aa87af9a7fedb9\",\n"
            + "  \"ref\": \"feature\",\n"
            + "  \"task\": \"deploy\",\n"
            + "  \"payload\": {\n"
            + "\n"
            + "  },\n"
            + "  \"environment\": \"production\",\n"
            + "  \"description\": null,\n"
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
            + "  \"created_at\": \"2016-02-10T16:44:45Z\",\n"
            + "  \"updated_at\": \"2016-02-10T16:44:45Z\",\n"
            + "  \"statuses_url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/deployments/3640119/statuses\",\n"
            + "  \"repository_url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle\"\n"
            + "}";

    public DeploymentSerializationTest() {
        super(Deployment.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_DEPLOYMENT;
    }

    @Override
    protected void checkDeserialized(Deployment deployment) {
        errorCollector.checkThat(deployment, allOf(Arrays.asList(
                property(Deployment::getId, is(3640119)),
                property(Deployment::getRef, is("feature")),
                property(Deployment::getSha, is("bef42899aa30ad50dfcc4465e0aa87af9a7fedb9")),
                property(Deployment::getTask, is("deploy")),
                property(Deployment::getPayload, is(any(Map.class))),
                property(Deployment::getEnvironment, is("production")),
                property(Deployment::getDescription, is(nullValue())),
                property(Deployment::getCreator, allOf(
                        property(User::getId, is(4105066)),
                        property(User::getLogin, is("Shredder121")),
                        property(User::getHtmlUrl, is("https://github.com/Shredder121"))
                )),
                property(Deployment::getCreatedAt, hasToString(startsWith("2016-02-10T16:44:45Z"))),
                property(Deployment::getUpdatedAt, hasToString(startsWith("2016-02-10T16:44:45Z")))
        )));
    }
}
