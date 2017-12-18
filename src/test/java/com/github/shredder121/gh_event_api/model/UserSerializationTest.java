package com.github.shredder121.gh_event_api.model;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class UserSerializationTest extends AbstractSerializationTest<User> {

    static String SAMPLE_USER
            = "{\n"
            + "  \"login\": \"octocat\",\n"
            + "  \"id\": 1,\n"
            + "  \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n"
            + "  \"gravatar_id\": \"\",\n"
            + "  \"url\": \"https://api.github.com/users/octocat\",\n"
            + "  \"html_url\": \"https://github.com/octocat\",\n"
            + "  \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n"
            + "  \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n"
            + "  \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n"
            + "  \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n"
            + "  \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n"
            + "  \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n"
            + "  \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n"
            + "  \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n"
            + "  \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n"
            + "  \"type\": \"User\",\n"
            + "  \"site_admin\": false,\n"
            + "  \"name\": \"monalisa octocat\",\n"
            + "  \"company\": \"GitHub\",\n"
            + "  \"blog\": \"https://github.com/blog\",\n"
            + "  \"location\": \"San Francisco\",\n"
            + "  \"email\": \"octocat@github.com\",\n"
            + "  \"hireable\": false,\n"
            + "  \"bio\": \"There once was...\",\n"
            + "  \"public_repos\": 2,\n"
            + "  \"public_gists\": 1,\n"
            + "  \"followers\": 20,\n"
            + "  \"following\": 0,\n"
            + "  \"created_at\": \"2008-01-14T04:33:35Z\",\n"
            + "  \"updated_at\": \"2008-01-14T04:33:35Z\"\n"
            + "}";

    public UserSerializationTest() {
        super(User.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_USER;
    }

    @Override
    protected void checkDeserialized(User user) {
        errorCollector.checkThat(user, allOf(asList(
                property(User::getId, is(1L)),
                property(User::getLogin, is("octocat")),
                property(User::getHtmlUrl, is("https://github.com/octocat"))
        )));
    }
}
