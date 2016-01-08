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

public class GitCommitSerializationTest extends AbstractSerializationTest<GitCommit> {

    private static final String SAMPLE_GIT_COMMIT
            = "{\n"
            + "  \"author\": {\n"
            + "    \"name\": \"baxterthehacker\",\n"
            + "    \"email\": \"baxterthehacker@users.noreply.github.com\",\n"
            + "    \"date\": \"2015-05-05T23:40:12Z\"\n"
            + "  },\n"
            + "  \"committer\": {\n"
            + "    \"name\": \"baxterthehacker\",\n"
            + "    \"email\": \"baxterthehacker@users.noreply.github.com\",\n"
            + "    \"date\": \"2015-05-05T23:40:12Z\"\n"
            + "  },\n"
            + "  \"message\": \"Initial commit\",\n"
            + "  \"tree\": {\n"
            + "    \"sha\": \"02b49ad0ba4f1acd9f06531b21e16a4ac5d341d0\",\n"
            + "    \"url\": \"https://api.github.com/repos/baxterthehacker/public-repo/git/trees/02b49ad0ba4f1acd9f06531b21e16a4ac5d341d0\"\n"
            + "  },\n"
            + "  \"url\": \"https://api.github.com/repos/baxterthehacker/public-repo/git/commits/9049f1265b7d61be4a8904a9a27120d2064dab3b\",\n"
            + "  \"comment_count\": 1\n"
            + "}";

    public GitCommitSerializationTest() {
        super(GitCommit.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_GIT_COMMIT;
    }

    @Override
    protected void checkDeserialized(GitCommit commit) {
        errorCollector.checkThat(commit.getMessage(), is("Initial commit"));
    }
}
