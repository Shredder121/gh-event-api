package com.github.shredder121.gh_event_api.handler.team_add;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterAndTheHackers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Team;

class TestHandler extends AbstractTestHandlerBean implements TeamAddHandler {

    @Override
    public void handle(TeamAddPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(TeamAddPayload::getTeam, teamMatchers()),
                property(TeamAddPayload::getRepository, is(BAXTERANDTHEHACKERS_PUBLIC_REPO)),
                property(TeamAddPayload::getOrganization, is(BAXTERANDTHEHACKERS_ORG)),
                property(TeamAddPayload::getSender, is(BAXTERANDTHEHACKERS))
        )));
        countDownLatch.countDown();
    }

    public Matcher<Team> teamMatchers() {
        return allOf(
                property(Team::getId, is(836012)),
                property(Team::getName, is("github")),
                property(Team::getSlug, is("github"))
        );
    }
}
