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
import static org.hamcrest.Matchers.iterableWithSize;

public class PushCommitSerializationTest extends AbstractSerializationTest<PushCommit> {

    private static final String SAMPLE_PUSH_COMMIT
            = "{\n"
            + "  \"id\": \"e2e9f9a1791c792bb5035031d0ef4df92f5788f9\",\n"
            + "  \"distinct\": true,\n"
            + "  \"message\": \"Remove mixin style jackson configuration\",\n"
            + "  \"timestamp\": \"2016-01-05T21:08:56+01:00\",\n"
            + "  \"url\": \"https://github.com/Shredder121/gh-event-api/commit/e2e9f9a1791c792bb5035031d0ef4df92f5788f9\",\n"
            + "  \"author\": {\n"
            + "    \"name\": \"Ruben Dijkstra\",\n"
            + "    \"email\": \"rubendijkstra123@gmail.com\",\n"
            + "    \"username\": \"Shredder121\"\n"
            + "  },\n"
            + "  \"committer\": {\n"
            + "    \"name\": \"Ruben Dijkstra\",\n"
            + "    \"email\": \"rubendijkstra123@gmail.com\",\n"
            + "    \"username\": \"Shredder121\"\n"
            + "  },\n"
            + "  \"added\": [\n"
            + "\n"
            + "  ],\n"
            + "  \"removed\": [\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/CommitCommentMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/GitCommitMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/LabelMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/LinkMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/PullRequestMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/PushCommitMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/RefMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/RepositoryMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/StatusBranchMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/StatusCommitMixin.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/UserMixin.java\"\n"
            + "  ],\n"
            + "  \"modified\": [\n"
            + "    \"pom.xml\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/CommitComment.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/GitCommit.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/Label.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/Link.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/PullRequest.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/PushCommit.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/Ref.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/Repository.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/StatusBranch.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/StatusCommit.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/User.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/GHEventApiModule.java\",\n"
            + "    \"src/main/java/com/github/shredder121/gh_event_api/model/json/package-info.java\"\n"
            + "  ]\n"
            + "}";

    public PushCommitSerializationTest() {
        super(PushCommit.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_PUSH_COMMIT;
    }

    @Override
    protected void checkDeserialized(PushCommit commit) {
        errorCollector.checkThat(commit.getMessage(), is("Remove mixin style jackson configuration"));

        errorCollector.checkThat(commit.getRemoved(), is(iterableWithSize(11)));
        errorCollector.checkThat(commit.getModified(), is(iterableWithSize(14)));
    }
}
