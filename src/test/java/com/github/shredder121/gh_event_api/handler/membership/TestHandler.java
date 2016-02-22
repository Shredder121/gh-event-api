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

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Organization;
import com.github.shredder121.gh_event_api.model.Team;
import com.github.shredder121.gh_event_api.model.User;

class TestHandler extends AbstractTestHandlerBean implements MembershipHandler {

    @Override
    public void handle(MembershipPayload payload) {
        errorCollector.checkThat(payload.getAction(), is("added"));

        errorCollector.checkThat(payload.getScope(), is("team"));

        errorCollector.checkThat(payload.getMember(), allOf(
                property(User::getId, is(2501)),
                property(User::getLogin, is("kdaigle")),
                property(User::getHtmlUrl, is("https://github.com/kdaigle"))
        ));

        errorCollector.checkThat(payload.getSender(), allOf(
                property(User::getId, is(6752317)),
                property(User::getLogin, is("baxterthehacker")),
                property(User::getHtmlUrl, is("https://github.com/baxterthehacker"))
        ));

        errorCollector.checkThat(payload.getTeam(), allOf(Arrays.asList(
                property(Team::getId, is(123456)),
                property(Team::getName, is("Contractors")),
                property(Team::getSlug, is("contractors")),
                property(Team::getPermission, is("admin")),
                property(Team::getUrl, is("https://api.github.com/teams/123456")),
                property(Team::getMembersUrl, is("https://api.github.com/teams/123456/members{/member}")),
                property(Team::getRepositoriesUrl, is("https://api.github.com/teams/123456/repos"))
        )));

        errorCollector.checkThat(payload.getOrganization(), allOf(
                property(Organization::getId, is(7649605)),
                property(Organization::getLogin, is("baxterandthehackers")),
                property(Organization::getUrl, is("https://api.github.com/orgs/baxterandthehackers"))
        ));

        countDownLatch.countDown();
    }
}
