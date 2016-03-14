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
package com.github.shredder121.gh_event_api.handler.membership;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.Kdaigle.KDAIGLE;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Team;

class TestHandler extends AbstractTestHandlerBean implements MembershipHandler {

    @Override
    public void handle(MembershipPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(MembershipPayload::getAction, is("added")),
                property(MembershipPayload::getScope, is("team")),
                property(MembershipPayload::getMember, is(KDAIGLE)),
                property(MembershipPayload::getSender, is(BAXTERTHEHACKER)),
                property(MembershipPayload::getTeam, teamMatchers())
        )));
        countDownLatch.countDown();
    }

    private static Matcher<Team> teamMatchers() {
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
