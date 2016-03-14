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
package com.github.shredder121.gh_event_api.handler.repository;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterAndTheHackers.BAXTERANDTHEHACKERS_NEW_REPOSITORY;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterAndTheHackers.BAXTERANDTHEHACKERS_ORG;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements RepositoryHandler {

    @Override
    public void handle(RepositoryPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(RepositoryPayload::getAction, is("created")),
                property(RepositoryPayload::getRepository, is(BAXTERANDTHEHACKERS_NEW_REPOSITORY)),
                property(RepositoryPayload::getOrganization, is(BAXTERANDTHEHACKERS_ORG)),
                property(RepositoryPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
