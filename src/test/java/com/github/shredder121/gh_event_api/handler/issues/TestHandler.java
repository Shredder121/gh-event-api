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

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Issue;
import com.github.shredder121.gh_event_api.model.Repository;

class TestHandler extends AbstractTestHandlerBean implements IssuesHandler {

    @Override
    public void handle(IssuesPayload payload) {
        errorCollector.checkThat(payload.getAction(), is("opened"));

        errorCollector.checkThat(payload.getIssue(), allOf(
                property(Issue::getId, is(73464126)),
                property(Issue::getNumber, is(2)),
                property(Issue::getTitle, is("Spelling error in the README file")),
                property(Issue::getBody, is("It looks like you accidently spelled 'commit' with two 't's.")),
                property(Issue::getCreatedAt, is(LocalDateTime.parse("2015-05-05T23:40:28").atZone(ZoneId.ofOffset("GMT", ZoneOffset.UTC)))),
                property(Issue::getUpdatedAt, is(LocalDateTime.parse("2015-05-05T23:40:28").atZone(ZoneId.ofOffset("GMT", ZoneOffset.UTC))))
        ));

        errorCollector.checkThat(payload.getRepository(), allOf(
                property(Repository::getFullName, is("baxterthehacker/public-repo")),
                property(Repository::getName, is("public-repo"))
        ));

        errorCollector.checkThat(payload.getIssue(), allOf(
                property(Issue::getNumber, is(2)),
                property(Issue::getTitle, is("Spelling error in the README file")),
                property(Issue::getState, is("open"))
        ));

        errorCollector.checkThat(payload.getSender().getLogin(), is("baxterthehacker"));

        countDownLatch.countDown();
    }
}
