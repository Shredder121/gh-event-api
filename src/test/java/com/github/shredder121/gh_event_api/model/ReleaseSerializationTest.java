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

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static org.hamcrest.Matchers.*;

public class ReleaseSerializationTest extends AbstractSerializationTest<Release> {

    private static final String SAMPLE_RELEASE
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/Shredder121/gh-event-api/releases/2525578\",\n"
            + "  \"assets_url\": \"https://api.github.com/repos/Shredder121/gh-event-api/releases/2525578/assets\",\n"
            + "  \"upload_url\": \"https://uploads.github.com/repos/Shredder121/gh-event-api/releases/2525578/assets{?name,label}\",\n"
            + "  \"html_url\": \"https://github.com/Shredder121/gh-event-api/releases/tag/0.4\",\n"
            + "  \"id\": 2525578,\n"
            + "  \"tag_name\": \"0.4\",\n"
            + "  \"target_commitish\": \"master\",\n"
            + "  \"name\": \"0.4\",\n"
            + "  \"draft\": false,\n"
            + "  \"author\": {\n"
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
            + "  \"prerelease\": false,\n"
            + "  \"created_at\": \"2016-01-31T19:56:26Z\",\n"
            + "  \"published_at\": \"2016-01-31T20:08:37Z\",\n"
            + "  \"assets\": [\n"
            + "    {\n"
            + "      \"url\": \"https://api.github.com/repos/Shredder121/gh-event-api/releases/assets/1263044\",\n"
            + "      \"id\": 1263044,\n"
            + "      \"name\": \"gh-event-api-0.4-full.jar\",\n"
            + "      \"label\": null,\n"
            + "      \"uploader\": {\n"
            + "        \"login\": \"Shredder121\",\n"
            + "        \"id\": 4105066,\n"
            + "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/4105066?v=3\",\n"
            + "        \"gravatar_id\": \"\",\n"
            + "        \"url\": \"https://api.github.com/users/Shredder121\",\n"
            + "        \"html_url\": \"https://github.com/Shredder121\",\n"
            + "        \"followers_url\": \"https://api.github.com/users/Shredder121/followers\",\n"
            + "        \"following_url\": \"https://api.github.com/users/Shredder121/following{/other_user}\",\n"
            + "        \"gists_url\": \"https://api.github.com/users/Shredder121/gists{/gist_id}\",\n"
            + "        \"starred_url\": \"https://api.github.com/users/Shredder121/starred{/owner}{/repo}\",\n"
            + "        \"subscriptions_url\": \"https://api.github.com/users/Shredder121/subscriptions\",\n"
            + "        \"organizations_url\": \"https://api.github.com/users/Shredder121/orgs\",\n"
            + "        \"repos_url\": \"https://api.github.com/users/Shredder121/repos\",\n"
            + "        \"events_url\": \"https://api.github.com/users/Shredder121/events{/privacy}\",\n"
            + "        \"received_events_url\": \"https://api.github.com/users/Shredder121/received_events\",\n"
            + "        \"type\": \"User\",\n"
            + "        \"site_admin\": false\n"
            + "      },\n"
            + "      \"content_type\": \"application/octet-stream\",\n"
            + "      \"state\": \"uploaded\",\n"
            + "      \"size\": 17252608,\n"
            + "      \"download_count\": 0,\n"
            + "      \"created_at\": \"2016-01-31T20:02:49Z\",\n"
            + "      \"updated_at\": \"2016-01-31T20:06:59Z\",\n"
            + "      \"browser_download_url\": \"https://github.com/Shredder121/gh-event-api/releases/download/0.4/gh-event-api-0.4-full.jar\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"tarball_url\": \"https://api.github.com/repos/Shredder121/gh-event-api/tarball/0.4\",\n"
            + "  \"zipball_url\": \"https://api.github.com/repos/Shredder121/gh-event-api/zipball/0.4\",\n"
            + "  \"body\": \"GitHub Event API Server 0.4\"\n"
            + "}";

    public ReleaseSerializationTest() {
        super(Release.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_RELEASE;
    }

    @Override
    protected void checkDeserialized(Release release) {
        errorCollector.checkThat(release.getId(), is(2525578));

        errorCollector.checkThat(release.getAssets(), contains(
                allOf(
                        property(Release.Asset::getId, is(1263044)),
                        property(Release.Asset::getName, is("gh-event-api-0.4-full.jar")),
                        property(Release.Asset::getContentType, hasToString("application/octet-stream"))
                )
        ));

        errorCollector.checkThat(release.getCreatedAt(), hasToString(containsString("2016-01-31T19:56:26Z")));

        errorCollector.checkThat(release.getPublishedAt(), hasToString(containsString("2016-01-31T20:08:37Z")));

        errorCollector.checkThat(release.getBody(), is("GitHub Event API Server 0.4"));

        errorCollector.checkThat(release.getDraft(), is(false));

        errorCollector.checkThat(release.getPrerelease(), is(false));

        errorCollector.checkThat(release.getAuthor(), allOf(
                property(User::getLogin, is("Shredder121")),
                property(User::getHtmlUrl, is("https://github.com/Shredder121"))
        ));
    }
}
