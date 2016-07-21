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

public class TeamSerializationTest extends AbstractSerializationTest<Team> {

    static String SAMPLE_TEAM
            = "{\n"
            + "  \"name\": \"Hackers\",\n"
            + "  \"id\": 1919074,\n"
            + "  \"slug\": \"hackers\",\n"
            + "  \"description\": \"All the members\",\n"
            + "  \"permission\": \"pull\",\n"
            + "  \"privacy\": \"closed\",\n"
            + "  \"url\": \"https://api.github.com/teams/1919074\",\n"
            + "  \"members_url\": \"https://api.github.com/teams/1919074/members{/member}\",\n"
            + "  \"repositories_url\": \"https://api.github.com/teams/1919074/repos\"\n"
            + "}";

    public TeamSerializationTest() {
        super(Team.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_TEAM;
    }

    @Override
    protected void checkDeserialized(Team team) {
        errorCollector.checkThat(team.getName(), is("Hackers"));

        errorCollector.checkThat(team.getSlug(), is("hackers"));

        errorCollector.checkThat(team.getDescription(), is("All the members"));

        errorCollector.checkThat(team.getPermission(), is("pull"));
    }
}
