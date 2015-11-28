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
package com.github.shredder121.gh_event_api.handler.status;

import static com.google.common.collect.Iterables.transform;
import static org.hamcrest.Matchers.*;

import java.util.Collection;

import org.springframework.boot.test.*;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;
import com.github.shredder121.gh_event_api.model.StatusBranch;
import com.github.shredder121.gh_event_api.model.Repository;

@SpringApplicationConfiguration(classes = {StatusHandlerTest.class, GHEventApiServer.class})
public class StatusHandlerTest extends AbstractHandlerTest {

    public StatusHandlerTest() {
        super("status", "fd376122b76bbda8e57c81a53d82b91c8389c28b");
    }

    @Bean
    public StatusHandler handlerBean() {
        return payload -> {
            errorCollector.checkThat(payload.getContext(), is("default"));
            errorCollector.checkThat(payload.getState(), is("success"));
            errorCollector.checkThat(payload.getSha(), is("9049f1265b7d61be4a8904a9a27120d2064dab3b"));
            errorCollector.checkThat(payload.getName(), is("baxterthehacker/public-repo"));
            errorCollector.checkThat(payload.getDescription(), is(nullValue()));
            errorCollector.checkThat(payload.getTargetUrl(), is(nullValue()));

            Collection<StatusBranch> branches = payload.getBranches();
            errorCollector.checkThat(transform(branches, StatusBranch::getName),
                    everyItem(either(is("master")).or(is("changes")).or(is("gh-pages"))));

            Repository repository = payload.getRepository();
            errorCollector.checkThat(repository.getName(), is("public-repo"));
            errorCollector.checkThat(repository.getFullName(), is("baxterthehacker/public-repo"));

            completion.countDown();
        };
    }
}
