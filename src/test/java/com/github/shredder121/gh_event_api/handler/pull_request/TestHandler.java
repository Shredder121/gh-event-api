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
package com.github.shredder121.gh_event_api.handler.pull_request;

import static com.github.shredder121.gh_event_api.testutil.DateTimeHelpers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Link;
import com.github.shredder121.gh_event_api.model.PullRequest;
import com.github.shredder121.gh_event_api.model.Ref;

class TestHandler extends AbstractTestHandlerBean implements PullRequestHandler {

    @Override
    public void handle(PullRequestPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(PullRequestPayload::getNumber, is(1)),
                property(PullRequestPayload::getAction, is("opened")),
                property(PullRequestPayload::getLabel, is(nullValue())),
                property(PullRequestPayload::getUser, is(nullValue())),
                property(PullRequestPayload::getPullRequest, pullRequestMatchers(payload)),
                property(PullRequestPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(PullRequestPayload::getOrganization, is(nullValue())),
                property(PullRequestPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    private static Matcher<PullRequest> pullRequestMatchers(PullRequestPayload payload) {
        return allOf(asList(
                property(PullRequest::getNumber, is(1)),
                property(PullRequest::getLocked, is(false)),
                property(PullRequest::getNumber, is(payload.getNumber())),
                property(PullRequest::getCreatedAt, is(dt("2015-05-05", "23:40:27"))),
                property(PullRequest::getLocked, is(false)),
                property(PullRequest::getState, is("open")),
                property(PullRequest::getTitle, is("Update the README with new information")),
                property(PullRequest::getHead, allOf(asList(
                        property(Ref::getRef, is("changes")),
                        property(Ref::getSha, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                        property(Ref::getLabel, is("baxterthehacker:changes")),
                        property(Ref::getUser, is(BAXTERTHEHACKER)),
                        property(Ref::getRepo, is(BAXTERTHEHACKER_PUBLIC_REPO))
                ))),
                property(PullRequest::getLinks, hasEntry(is("self"), property(Link::getHref, is("https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"))))
        ));
    }
}
