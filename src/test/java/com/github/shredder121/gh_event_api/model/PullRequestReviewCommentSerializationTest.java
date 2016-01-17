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

import static org.hamcrest.Matchers.*;

public class PullRequestReviewCommentSerializationTest extends AbstractSerializationTest<Comment> {

    private static final String SAMPLE_PULL_REQUEST_REVIEW_COMMENT
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/Shredder121/gh-event-api/pulls/comments/49949630\",\n"
            + "  \"id\": 49949630,\n"
            + "  \"diff_hunk\": \"@@ -39,6 +39,12 @@\\n     Integer position;\\n \\n     /**\\n+     * The original line index of the diff.\",\n"
            + "  \"path\": \"src/main/java/com/github/shredder121/gh_event_api/model/Comment.java\",\n"
            + "  \"position\": 13,\n"
            + "  \"original_position\": 13,\n"
            + "  \"commit_id\": \"81c7fdaa5f51059e4752f48313da93f398b8b0d1\",\n"
            + "  \"original_commit_id\": \"81c7fdaa5f51059e4752f48313da93f398b8b0d1\",\n"
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
            + "  \"body\": \"`line number might be better`.\",\n"
            + "  \"created_at\": \"2016-01-17T17:55:03Z\",\n"
            + "  \"updated_at\": \"2016-01-17T17:55:03Z\",\n"
            + "  \"html_url\": \"https://github.com/Shredder121/gh-event-api/pull/19#discussion_r49949630\",\n"
            + "  \"pull_request_url\": \"https://api.github.com/repos/Shredder121/gh-event-api/pulls/19\",\n"
            + "  \"_links\": {\n"
            + "    \"self\": {\n"
            + "      \"href\": \"https://api.github.com/repos/Shredder121/gh-event-api/pulls/comments/49949630\"\n"
            + "    },\n"
            + "    \"html\": {\n"
            + "      \"href\": \"https://github.com/Shredder121/gh-event-api/pull/19#discussion_r49949630\"\n"
            + "    },\n"
            + "    \"pull_request\": {\n"
            + "      \"href\": \"https://api.github.com/repos/Shredder121/gh-event-api/pulls/19\"\n"
            + "    }\n"
            + "  }\n"
            + "}";

    public PullRequestReviewCommentSerializationTest() {
        super(Comment.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_PULL_REQUEST_REVIEW_COMMENT;
    }

    @Override
    protected void checkDeserialized(Comment comment) {
        errorCollector.checkThat(comment.getDiffHunk(), containsString("The original line index"));
        errorCollector.checkThat(comment.getBody(), is("`line number might be better`."));

        errorCollector.checkThat(comment.getCommitId(), is(
                both(equalTo("81c7fdaa5f51059e4752f48313da93f398b8b0d1"))
                .and(equalTo(comment.getOriginalCommitId()))));

        errorCollector.checkThat(comment.getPosition(), is(
                both(equalTo(13))
                .and(equalTo(comment.getOriginalPosition()))));
    }
}
