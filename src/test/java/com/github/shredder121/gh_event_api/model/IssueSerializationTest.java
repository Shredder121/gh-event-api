package com.github.shredder121.gh_event_api.model;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;

public class IssueSerializationTest extends AbstractSerializationTest<Issue> {

    static String SAMPLE_ISSUE
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/issues/28\",\n"
            + "  \"labels_url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/issues/28/labels{/name}\",\n"
            + "  \"comments_url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/issues/28/comments\",\n"
            + "  \"events_url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/issues/28/events\",\n"
            + "  \"html_url\": \"https://github.com/Shredder121-me/yummy-octo-turtle/issues/28\",\n"
            + "  \"id\": 125840141,\n"
            + "  \"number\": 28,\n"
            + "  \"title\": \"Test\",\n"
            + "  \"user\": {\n"
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
            + "  \"labels\": [\n"
            + "\n"
            + "  ],\n"
            + "  \"state\": \"open\",\n"
            + "  \"locked\": false,\n"
            + "  \"assignee\": null,\n"
            + "  \"milestone\": null,\n"
            + "  \"comments\": 0,\n"
            + "  \"created_at\": \"2016-01-10T19:33:39Z\",\n"
            + "  \"updated_at\": \"2016-01-10T19:33:39Z\",\n"
            + "  \"closed_at\": null,\n"
            + "  \"body\": \"\"\n"
            + "}";

    public IssueSerializationTest() {
        super(Issue.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_ISSUE;
    }

    @Override
    protected void checkDeserialized(Issue issue) {
        errorCollector.checkThat(issue.getState(), is("open"));
        errorCollector.checkThat(issue.getBody(), isEmptyString());
    }
}
