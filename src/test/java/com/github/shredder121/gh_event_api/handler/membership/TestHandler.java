package com.github.shredder121.gh_event_api.handler.membership;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.Kdaigle.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Team;

class TestHandler extends AbstractTestHandlerBean implements MembershipHandler {

    @Override
    public void accept(MembershipPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(MembershipPayload::getAction, is("added")),
                property(MembershipPayload::getScope, is("team")),
                property(MembershipPayload::getMember, is(KDAIGLE)),
                property(MembershipPayload::getSender, is(BAXTERTHEHACKER)),
                property(MembershipPayload::getTeam, teamMatchers())
        )));
        countDownLatch.countDown();
    }

    public Matcher<Team> teamMatchers() {
        return allOf(asList(
                property(Team::getId, is(123456)),
                property(Team::getName, is("Contractors")),
                property(Team::getSlug, is("contractors")),
                property(Team::getPermission, is("admin")),
                property(Team::getUrl, is("https://api.github.com/teams/123456")),
                property(Team::getMembersUrl, is("https://api.github.com/teams/123456/members{/member}")),
                property(Team::getRepositoriesUrl, is("https://api.github.com/teams/123456/repos"))
        ));
    }
}
