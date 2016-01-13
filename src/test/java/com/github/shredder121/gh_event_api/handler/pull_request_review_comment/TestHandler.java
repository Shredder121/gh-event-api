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
package com.github.shredder121.gh_event_api.handler.pull_request_review_comment;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.PullRequest;

class TestHandler extends AbstractTestHandlerBean implements PullRequestReviewCommentHandler {

    @Override
    public void handle(PullRequestReviewCommentPayload payload) {
        errorCollector.checkThat(payload.getAction(), is("created"));
        errorCollector.checkThat(payload.getPullRequest(), allOf(
                property(PullRequest::getNumber, is(1)),
                property(PullRequest::getTitle, is("Update the README with new information"))
        ));
        errorCollector.checkThat(payload.getComment(), allOf(
                property(map -> map.get("body"), is("Maybe you should use more emojji on this line.")),
                property(map -> map.get("path"), is("README.md"))));

        countDownLatch.countDown();
    }
}
