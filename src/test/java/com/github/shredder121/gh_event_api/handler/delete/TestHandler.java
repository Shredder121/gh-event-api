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
package com.github.shredder121.gh_event_api.handler.delete;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER_PUBLIC_REPO;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements DeleteHandler {

    @Override
    public void handle(DeletePayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(DeletePayload::getRefType, is("tag")),
                property(DeletePayload::getRef, is("simple-tag")),
                property(DeletePayload::getPusherType, is("user")),
                property(DeletePayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(DeletePayload::getOrganization, is(nullValue())),
                property(DeletePayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
