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

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

class TestHandler extends AbstractTestHandlerBean implements CreateHandler {

    @Override
    public void handle(CreatePayload payload) {
        errorCollector.checkThat(payload.getDescription(), is(""));
        errorCollector.checkThat(payload.getRefType(), is("tag"));
        errorCollector.checkThat(payload.getRef(), is("0.0.1"));
        errorCollector.checkThat(payload.getMasterBranch(), is("master"));
        errorCollector.checkThat(payload.getPusherType(), is("user"));

        errorCollector.checkThat(payload.getRepository(), allOf(
                property(Repository::getFullName, is("baxterthehacker/public-repo")),
                property(Repository::getName, is("public-repo"))
        ));

        errorCollector.checkThat(payload.getSender().getLogin(), is("baxterthehacker"));

        countDownLatch.countDown();
    }
}
