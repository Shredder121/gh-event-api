/*
 * Copyright 2015 Shredder121.
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

import org.springframework.boot.test.*;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

@SpringApplicationConfiguration(classes = {ForkHandlerTest.class, GHEventApiServer.class})
public class ForkHandlerTest extends AbstractHandlerTest {

    public ForkHandlerTest() {
        super("fork", "d4f7cde1c2a894a96b9f2c651ee4744e31a01a35");
    }

    @Bean
    public ForkHandler handlerBean() {
        return payload -> {
            User sender = payload.getSender();
            errorCollector.checkThat(sender.getLogin(), is("baxterandthehackers"));

            Repository repository = payload.getRepository();
            errorCollector.checkThat(repository.getOwner().getLogin(), is("baxterthehacker"));
            errorCollector.checkThat(repository.getForks(), is(1L));

            Repository forkee = payload.getForkee();
            errorCollector.checkThat(forkee.getName(), is(repository.getName()));
            errorCollector.checkThat(forkee.getForksCount(), is(0L));

            errorCollector.checkThat(forkee.getFullName(), containsString(sender.getLogin()));
            errorCollector.checkThat(forkee.getForksCount(), is(lessThan(repository.getForksCount())));

            completion.countDown();
        };
    }
}
