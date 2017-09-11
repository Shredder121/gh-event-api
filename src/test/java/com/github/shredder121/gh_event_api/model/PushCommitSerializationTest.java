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

public class PushCommitSerializationTest extends AbstractSerializationTest<PushCommit> {

    private static final String SAMPLE_PUSH_COMMIT
            = "{\n"
            + "  \"id\": \"f2591cd0a959f15a3b274a5182f3ccc0e35c1ec9\",\n"
            + "  \"tree_id\": \"af9847bf3b89671bfe40c9993b4574e2c564c40f\",\n"
            + "  \"distinct\": true,\n"
            + "  \"message\": \"Add the .cache folder to gitignore\",\n"
            + "  \"timestamp\": \"2016-04-09T19:31:07+02:00\",\n"
            + "  \"url\": \"https://github.com/Shredder121/gh-event-api/commit/f2591cd0a959f15a3b274a5182f3ccc0e35c1ec9\",\n"
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
            + "\n"
            + "  ],\n"
            + "  \"modified\": [\n"
            + "    \".gitignore\"\n"
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
        errorCollector.checkThat(commit.getId(), is("f2591cd0a959f15a3b274a5182f3ccc0e35c1ec9"));
        errorCollector.checkThat(commit.getTreeId(), is("af9847bf3b89671bfe40c9993b4574e2c564c40f"));
        errorCollector.checkThat(commit.getMessage(), is("Add the .cache folder to gitignore"));

        errorCollector.checkThat(commit.getAdded(), is(empty()));
        errorCollector.checkThat(commit.getRemoved(), is(empty()));
        errorCollector.checkThat(commit.getModified(), contains(
                equalTo(".gitignore")
        ));
    }
}
