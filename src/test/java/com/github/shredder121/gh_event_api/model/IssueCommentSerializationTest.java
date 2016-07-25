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

import static org.hamcrest.Matchers.is;

public class IssueCommentSerializationTest extends AbstractSerializationTest<Comment> {

    static String SAMPLE_ISSUE_COMMENT
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/issues/comments/172632780\",\n"
            + "  \"html_url\": \"https://github.com/Shredder121-me/yummy-octo-turtle/pull/28#issuecomment-172632780\",\n"
            + "  \"issue_url\": \"https://api.github.com/repos/Shredder121-me/yummy-octo-turtle/issues/28\",\n"
            + "  \"id\": 172632780,\n"
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
            + "  \"created_at\": \"2016-01-18T19:46:00Z\",\n"
            + "  \"updated_at\": \"2016-01-18T19:46:00Z\",\n"
            + "  \"body\": \"What do you think?\"\n"
            + "}";

    public IssueCommentSerializationTest() {
        super(Comment.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_ISSUE_COMMENT;
    }

    @Override
    protected void checkDeserialized(Comment comment) {
        errorCollector.checkThat(comment.getId(), is(172632780));
        errorCollector.checkThat(comment.getBody(), is("What do you think?"));
    }
}
