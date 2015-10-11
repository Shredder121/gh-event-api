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
package com.github.shredder121.gh_event_api.handler.pull_request;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;
import com.github.shredder121.gh_event_api.model.PullRequest;
import com.github.shredder121.gh_event_api.model.Ref;
import com.github.shredder121.gh_event_api.model.Repository;

@SpringApplicationConfiguration(classes = {PullRequestHandlerTest.class, GHEventApiServer.class})
public class PullRequestHandlerTest extends AbstractHandlerTest {

    public PullRequestHandlerTest() {
        super("pull_request");
    }

    @Bean
    public PullRequestHandler handlerBean() {
        return payload -> {
            errorCollector.checkThat(payload.getNumber(), is(1));
            errorCollector.checkThat(payload.getAction(), is("opened"));
            errorCollector.checkThat("payload is not from a labeled or unlabeled event",
                    payload.getLabel(), is(nullValue()));
            errorCollector.checkThat("paload is not from an assigned or unassigned event",
                    payload.getUser(), is(nullValue()));

            PullRequest pullRequest = payload.getPullRequest();
            errorCollector.checkThat(pullRequest.getNumber(), is(payload.getNumber()));
            errorCollector.checkThat(pullRequest.getCreated_at(), is(LocalDateTime.parse("2015-05-05T23:40:27").atZone(ZoneId.ofOffset("GMT", ZoneOffset.UTC))));
            errorCollector.checkThat(pullRequest.getLocked(), is(false));
            errorCollector.checkThat(pullRequest.getState(), is("open"));
            errorCollector.checkThat(pullRequest.getTitle(), is("Update the README with new information"));
            errorCollector.checkThat(pullRequest.getLinks()
                    .get("self").getHref(), is(pullRequest.getUrl()));

            Ref head = pullRequest.getHead();
            errorCollector.checkThat(head.getSha(), is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c"));
            errorCollector.checkThat(head.getRef(), is("changes"));
            errorCollector.checkThat(head.getLabel(), is("baxterthehacker:changes"));
            errorCollector.checkThat(head.getUser().getLogin(), is("baxterthehacker"));

            Repository repo = head.getRepo();
            errorCollector.checkThat(repo.getName(), is("public-repo"));
            errorCollector.checkThat(repo.getFullName(), is("baxterthehacker/public-repo"));

            completion.countDown();
        };
    }
}
