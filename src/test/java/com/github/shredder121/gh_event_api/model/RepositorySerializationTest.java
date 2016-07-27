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

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static org.hamcrest.Matchers.is;

public class RepositorySerializationTest extends AbstractSerializationTest<Repository> {

    static String SAMPLE_REPOSITORY
            = "{\n"
            + "  \"id\": 35129377,\n"
            + "  \"name\": \"public-repo\",\n"
            + "  \"full_name\": \"baxterthehacker/public-repo\",\n"
            + "  \"owner\": {\n"
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
            + "  \"private\": false,\n"
            + "  \"html_url\": \"https://github.com/baxterthehacker/public-repo\",\n"
            + "  \"description\": \"\",\n"
            + "  \"fork\": false,\n"
            + "  \"url\": \"https://api.github.com/repos/baxterthehacker/public-repo\",\n"
            + "  \"forks_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/forks\",\n"
            + "  \"keys_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}\",\n"
            + "  \"collaborators_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}\",\n"
            + "  \"teams_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/teams\",\n"
            + "  \"hooks_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/hooks\",\n"
            + "  \"issue_events_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}\",\n"
            + "  \"events_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/events\",\n"
            + "  \"assignees_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}\",\n"
            + "  \"branches_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}\",\n"
            + "  \"tags_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/tags\",\n"
            + "  \"blobs_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}\",\n"
            + "  \"git_tags_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}\",\n"
            + "  \"git_refs_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}\",\n"
            + "  \"trees_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}\",\n"
            + "  \"statuses_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}\",\n"
            + "  \"languages_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/languages\",\n"
            + "  \"stargazers_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/stargazers\",\n"
            + "  \"contributors_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/contributors\",\n"
            + "  \"subscribers_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/subscribers\",\n"
            + "  \"subscription_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/subscription\",\n"
            + "  \"commits_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}\",\n"
            + "  \"git_commits_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}\",\n"
            + "  \"comments_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}\",\n"
            + "  \"issue_comment_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}\",\n"
            + "  \"contents_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}\",\n"
            + "  \"compare_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}\",\n"
            + "  \"merges_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/merges\",\n"
            + "  \"archive_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}\",\n"
            + "  \"downloads_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/downloads\",\n"
            + "  \"issues_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}\",\n"
            + "  \"pulls_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}\",\n"
            + "  \"milestones_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}\",\n"
            + "  \"notifications_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}\",\n"
            + "  \"labels_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}\",\n"
            + "  \"releases_url\": \"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}\",\n"
            + "  \"created_at\": \"2015-05-05T23:40:12Z\",\n"
            + "  \"updated_at\": \"2015-05-05T23:40:12Z\",\n"
            + "  \"pushed_at\": \"2015-05-05T23:40:26Z\",\n"
            + "  \"git_url\": \"git://github.com/baxterthehacker/public-repo.git\",\n"
            + "  \"ssh_url\": \"git@github.com:baxterthehacker/public-repo.git\",\n"
            + "  \"clone_url\": \"https://github.com/baxterthehacker/public-repo.git\",\n"
            + "  \"svn_url\": \"https://github.com/baxterthehacker/public-repo\",\n"
            + "  \"homepage\": null,\n"
            + "  \"size\": 0,\n"
            + "  \"stargazers_count\": 0,\n"
            + "  \"watchers_count\": 0,\n"
            + "  \"language\": null,\n"
            + "  \"has_issues\": true,\n"
            + "  \"has_downloads\": true,\n"
            + "  \"has_wiki\": true,\n"
            + "  \"has_pages\": true,\n"
            + "  \"forks_count\": 0,\n"
            + "  \"mirror_url\": null,\n"
            + "  \"open_issues_count\": 1,\n"
            + "  \"forks\": 0,\n"
            + "  \"open_issues\": 1,\n"
            + "  \"watchers\": 0,\n"
            + "  \"default_branch\": \"master\"\n"
            + "}";

    public RepositorySerializationTest() {
        super(Repository.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_REPOSITORY;
    }

    @Override
    protected void checkDeserialized(Repository repository) {
        errorCollector.checkThat(repository, is(BAXTERTHEHACKER_PUBLIC_REPO));
    }
}
