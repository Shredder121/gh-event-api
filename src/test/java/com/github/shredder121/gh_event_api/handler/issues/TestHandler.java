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
package com.github.shredder121.gh_event_api.handler.issues;

import static com.github.shredder121.gh_event_api.testutil.DateTimeHelpers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER_PUBLIC_REPO;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Issue;

class TestHandler extends AbstractTestHandlerBean implements IssuesHandler {

    @Override
    public void handle(IssuesPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(IssuesPayload::getAction, is("opened")),
                property(IssuesPayload::getIssue, issueMatchers()),
                property(IssuesPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(IssuesPayload::getOrganization, is(nullValue())),
                property(IssuesPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    private static Matcher<Issue> issueMatchers() {
        return allOf(asList(
                property(Issue::getId, is(73464126)),
                property(Issue::getNumber, is(2)),
                property(Issue::getState, is("open")),
                property(Issue::getTitle, is("Spelling error in the README file")),
                property(Issue::getBody, is("It looks like you accidently spelled 'commit' with two 't's.")),
                property(Issue::getCreatedAt, is(dt("2015-05-05", "23:40:28"))),
                property(Issue::getUpdatedAt, is(dt("2015-05-05", "23:40:28")))
        ));
    }
}
