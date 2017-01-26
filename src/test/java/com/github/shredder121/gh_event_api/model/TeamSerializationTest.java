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
