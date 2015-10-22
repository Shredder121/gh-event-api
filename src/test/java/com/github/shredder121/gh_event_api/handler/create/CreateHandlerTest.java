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
package com.github.shredder121.gh_event_api.handler.create;

import static org.hamcrest.Matchers.is;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {CreateHandlerTest.class, GHEventApiServer.class})
public class CreateHandlerTest extends AbstractHandlerTest {

    public CreateHandlerTest() {
        super("create", "692767e05058a25010d4aa30f2c5f324350c9a4c");
    }

    @Bean
    public CreateHandler handlerBean() {
        return payload -> {
            errorCollector.checkThat(payload.getDescription(), is(""));
            errorCollector.checkThat(payload.getRef_type(), is("tag"));
            errorCollector.checkThat(payload.getRef(), is("0.0.1"));
            errorCollector.checkThat(payload.getMaster_branch(), is("master"));

            completion.countDown();
        };
    }
}
