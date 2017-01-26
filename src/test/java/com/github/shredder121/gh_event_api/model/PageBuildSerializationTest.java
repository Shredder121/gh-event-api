package com.github.shredder121.gh_event_api.model;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

public class PageBuildSerializationTest extends AbstractSerializationTest<PageBuild> {

    static String SAMPLE_PAGE_BUILD
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/Shredder121-me/Shredder121-me.github.io/pages/builds/25700882\",\n"
            + "  \"status\": \"built\",\n"
            + "  \"error\": {\n"
            + "    \"message\": null\n"
            + "  },\n"
            + "  \"pusher\": {\n"
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
            + "  \"commit\": \"d1e0310b2326a1842aa4f2864d3af1cbcdcf5b45\",\n"
            + "  \"duration\": 3567,\n"
            + "  \"created_at\": \"2016-02-10T20:10:21Z\",\n"
            + "  \"updated_at\": \"2016-02-10T20:10:25Z\"\n"
            + "}";

    public PageBuildSerializationTest() {
        super(PageBuild.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_PAGE_BUILD;
    }

    @Override
    protected void checkDeserialized(PageBuild pageBuild) {
        errorCollector.checkThat(pageBuild, allOf(Arrays.asList(
                property(PageBuild::getUrl, is("https://api.github.com/repos/Shredder121-me/Shredder121-me.github.io/pages/builds/25700882")),
                property(PageBuild::getStatus, is("built")),
                property(PageBuild::getError, hasProperty("message", nullValue())),
                property(PageBuild::getPusher, allOf(
                        property(User::getId, is(4105066)),
                        property(User::getLogin, is("Shredder121")),
                        property(User::getHtmlUrl, is("https://github.com/Shredder121"))
                )),
                property(PageBuild::getCommit, is("d1e0310b2326a1842aa4f2864d3af1cbcdcf5b45")),
                property(PageBuild::getDuration, is(3567L)),
                property(PageBuild::getCreatedAt, hasToString(startsWith("2016-02-10T20:10:21Z"))),
                property(PageBuild::getUpdatedAt, hasToString(startsWith("2016-02-10T20:10:25Z")))
        )));
    }
}
