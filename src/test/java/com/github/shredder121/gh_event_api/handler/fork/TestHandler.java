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
package com.github.shredder121.gh_event_api.handler.fork;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterAndTheHackers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements ForkHandler {

    @Override
    public void handle(ForkPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(ForkPayload::getSender, is(BAXTERANDTHEHACKERS)),
                property(ForkPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(ForkPayload::getForkee, is(BAXTERANDTHEHACKERS_PUBLIC_REPO))
        )));
        countDownLatch.countDown();
    }
}
