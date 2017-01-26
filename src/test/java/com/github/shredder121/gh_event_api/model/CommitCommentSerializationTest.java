package com.github.shredder121.gh_event_api.model;

import static org.hamcrest.Matchers.is;

public class CommitCommentSerializationTest extends AbstractSerializationTest<Comment> {

    static String SAMPLE_COMMIT_COMMENT
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/baxterthehacker/public-repo/comments/11056394\",\n"
            + "  \"html_url\": \"https://github.com/baxterthehacker/public-repo/commit/9049f1265b7d61be4a8904a9a27120d2064dab3b#commitcomment-11056394\",\n"
            + "  \"id\": 11056394,\n"
            + "  \"user\": {\n"
            + "    \"login\": \"baxterthehacker\",\n"
            + "    \"id\": 6752317,\n"
            + "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/6752317?v=3\",\n"
            + "    \"gravatar_id\": \"\",\n"
            + "    \"url\": \"https://api.github.com/users/baxterthehacker\",\n"
            + "    \"html_url\": \"https://github.com/baxterthehacker\",\n"
            + "    \"followers_url\": \"https://api.github.com/users/baxterthehacker/followers\",\n"
            + "    \"following_url\": \"https://api.github.com/users/baxterthehacker/following{/other_user}\",\n"
            + "    \"gists_url\": \"https://api.github.com/users/baxterthehacker/gists{/gist_id}\",\n"
            + "    \"starred_url\": \"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}\",\n"
            + "    \"subscriptions_url\": \"https://api.github.com/users/baxterthehacker/subscriptions\",\n"
            + "    \"organizations_url\": \"https://api.github.com/users/baxterthehacker/orgs\",\n"
            + "    \"repos_url\": \"https://api.github.com/users/baxterthehacker/repos\",\n"
            + "    \"events_url\": \"https://api.github.com/users/baxterthehacker/events{/privacy}\",\n"
            + "    \"received_events_url\": \"https://api.github.com/users/baxterthehacker/received_events\",\n"
            + "    \"type\": \"User\",\n"
            + "    \"site_admin\": false\n"
            + "  },\n"
            + "  \"position\": null,\n"
            + "  \"line\": null,\n"
            + "  \"path\": null,\n"
            + "  \"commit_id\": \"9049f1265b7d61be4a8904a9a27120d2064dab3b\",\n"
            + "  \"created_at\": \"2015-05-05T23:40:29Z\",\n"
            + "  \"updated_at\": \"2015-05-05T23:40:29Z\",\n"
            + "  \"body\": \"This is a really good change! :+1:\"\n"
            + "}";

    public CommitCommentSerializationTest() {
        super(Comment.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_COMMIT_COMMENT;
    }

    @Override
    protected void checkDeserialized(Comment comment) {
        errorCollector.checkThat(comment.getBody(), is("This is a really good change! :+1:"));
        errorCollector.checkThat(comment.getCommitId(), is("9049f1265b7d61be4a8904a9a27120d2064dab3b"));
    }
}
