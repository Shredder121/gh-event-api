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
package com.github.shredder121.gh_event_api.handler.push;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.PushCommit;

class TestHandler extends AbstractTestHandlerBean implements PushHandler {

    @Override
    public void handle(PushPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(PushPayload::getRef, is("refs/heads/changes")),
                property(PushPayload::getBefore, is("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(PushPayload::getAfter, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                property(PushPayload::getCreated, is(false)),
                property(PushPayload::getDeleted, is(false)),
                property(PushPayload::getForced, is(false)),
                property(PushPayload::getCommits, commitsMatchers()),
                property(PushPayload::getHeadCommit, headCommitMatchers()),
                property(PushPayload::getSender, is(BAXTERTHEHACKER)),
                property(PushPayload::getOrganization, is(nullValue())),
                property(PushPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO))
        )));
        countDownLatch.countDown();
    }

    private static Matcher<Iterable<? extends PushCommit>> commitsMatchers() {
        return contains(
                allOf(asList(
                        property(PushCommit::getId, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                        property(PushCommit::getTreeId, is("f9d2a07e9488b91af2641b26b9407fe22a451433")),
                        property(PushCommit::getMessage, is("Update README.md"))
                ))
        );
    }

    private static Matcher<PushCommit> headCommitMatchers() {
        return allOf(asList(
                property(PushCommit::getId, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                property(PushCommit::getTreeId, is("f9d2a07e9488b91af2641b26b9407fe22a451433")),
                property(PushCommit::getMessage, is("Update README.md"))
        ));
    }
}
