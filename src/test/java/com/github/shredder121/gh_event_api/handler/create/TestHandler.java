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
package com.github.shredder121.gh_event_api.handler.create;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements CreateHandler {

    @Override
    public void handle(CreatePayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(CreatePayload::getDescription, is("")),
                property(CreatePayload::getRefType, is("tag")),
                property(CreatePayload::getRef, is("0.0.1")),
                property(CreatePayload::getMasterBranch, is("master")),
                property(CreatePayload::getPusherType, is("user")),
                property(CreatePayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(CreatePayload::getOrganization, is(nullValue())),
                property(CreatePayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
