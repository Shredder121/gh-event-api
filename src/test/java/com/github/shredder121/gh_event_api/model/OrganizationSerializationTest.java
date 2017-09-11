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

import java.util.Arrays;

public class OrganizationSerializationTest extends AbstractSerializationTest<Organization> {

    private static final String SAMPLE_ORGANIZATION
            = "{\n"
            + "  \"login\": \"Shredder121-me\",\n"
            + "  \"id\": 15276443,\n"
            + "  \"url\": \"https://api.github.com/orgs/Shredder121-me\",\n"
            + "  \"repos_url\": \"https://api.github.com/orgs/Shredder121-me/repos\",\n"
            + "  \"events_url\": \"https://api.github.com/orgs/Shredder121-me/events\",\n"
            + "  \"hooks_url\": \"https://api.github.com/orgs/Shredder121-me/hooks\",\n"
            + "  \"issues_url\": \"https://api.github.com/orgs/Shredder121-me/issues\",\n"
            + "  \"members_url\": \"https://api.github.com/orgs/Shredder121-me/members{/member}\",\n"
            + "  \"public_members_url\": \"https://api.github.com/orgs/Shredder121-me/public_members{/member}\",\n"
            + "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/15276443?v=3\",\n"
            + "  \"description\": \"\"\n"
            + "}";

    public OrganizationSerializationTest() {
        super(Organization.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_ORGANIZATION;
    }

    @Override
    protected void checkDeserialized(Organization organization) {
        errorCollector.checkThat(organization, allOf(Arrays.asList(
                property(Organization::getLogin, is("Shredder121-me")),
                property(Organization::getId, is(15276443)),
                property(Organization::getUrl, is("https://api.github.com/orgs/Shredder121-me")),
                property(Organization::getReposUrl, is("https://api.github.com/orgs/Shredder121-me/repos")),
                property(Organization::getEventsUrl, is("https://api.github.com/orgs/Shredder121-me/events")),
                property(Organization::getHooksUrl, is("https://api.github.com/orgs/Shredder121-me/hooks")),
                property(Organization::getIssuesUrl, is("https://api.github.com/orgs/Shredder121-me/issues")),
                property(Organization::getMembersUrl, is("https://api.github.com/orgs/Shredder121-me/members{/member}")),
                property(Organization::getPublicMembersUrl, is("https://api.github.com/orgs/Shredder121-me/public_members{/member}")),
                property(Organization::getAvatarUrl, is("https://avatars.githubusercontent.com/u/15276443?v=3")),
                property(Organization::getDescription, isEmptyString())
        )));
    }
}
