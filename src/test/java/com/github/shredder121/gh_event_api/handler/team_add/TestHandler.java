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
package com.github.shredder121.gh_event_api.handler.team_add;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.Team;

class TestHandler extends AbstractTestHandlerBean implements TeamAddHandler {

    @Override
    public void handle(TeamAddPayload payload) {
        errorCollector.checkThat(payload.getTeam(), allOf(
                property(Team::getId, is(836012)),
                property(Team::getName, is("github")),
                property(Team::getSlug, is("github"))
        ));

        errorCollector.checkThat(payload.getRepository(), allOf(
                property(Repository::getName, is("public-repo")),
                property(Repository::getFullName, is("baxterandthehackers/public-repo"))
        ));

        errorCollector.checkThat(payload.getSender().getLogin(), is("baxterandthehackers"));

        countDownLatch.countDown();
    }
}
