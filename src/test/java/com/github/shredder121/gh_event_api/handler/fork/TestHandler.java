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

import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

class TestHandler extends AbstractTestHandlerBean implements ForkHandler {

    @Override
    public void handle(ForkPayload payload) {
        User sender = payload.getSender();
        errorCollector.checkThat(sender.getLogin(), is("baxterandthehackers"));

        Repository repository = payload.getRepository();
        errorCollector.checkThat(repository.getOwner().getLogin(), is("baxterthehacker"));
        errorCollector.checkThat(repository.getForks(), is(1L));

        errorCollector.checkThat(payload.getOrganization(), is(nullValue()));

        Repository forkee = payload.getForkee();
        errorCollector.checkThat(forkee.getName(), is(repository.getName()));
        errorCollector.checkThat(forkee.getForksCount(), is(0L));

        errorCollector.checkThat(forkee.getFullName(), containsString(sender.getLogin()));
        errorCollector.checkThat(forkee.getForksCount(), is(lessThan(repository.getForksCount())));

        countDownLatch.countDown();
    }
}
