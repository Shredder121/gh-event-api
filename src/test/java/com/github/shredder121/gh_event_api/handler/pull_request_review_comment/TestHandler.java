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

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Comment;
import com.github.shredder121.gh_event_api.model.PullRequest;

class TestHandler extends AbstractTestHandlerBean implements PullRequestReviewCommentHandler {

    @Override
    public void handle(PullRequestReviewCommentPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(PullRequestReviewCommentPayload::getAction, is("created")),
                property(PullRequestReviewCommentPayload::getPullRequest, pullRequestMatchers()),
                property(PullRequestReviewCommentPayload::getComment, commentMatchers()),
                property(PullRequestReviewCommentPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(PullRequestReviewCommentPayload::getOrganization, is(nullValue())),
                property(PullRequestReviewCommentPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    private static Matcher<PullRequest> pullRequestMatchers() {
        return allOf(
                property(PullRequest::getNumber, is(1)),
                property(PullRequest::getTitle, is("Update the README with new information"))
        );
    }

    private static Matcher<Comment> commentMatchers() {
        return allOf(asList(
                property(Comment::getBody, is("Maybe you should use more emojji on this line.")),
                property(Comment::getPath, is("README.md")),
                property(Comment::getPosition, is(1)),
                property(Comment::getOriginalPosition, is(1)),
                property(Comment::getCommitId, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                property(Comment::getOriginalCommitId, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                property(Comment::getDiffHunk, containsString("-# public-repo"))));
    }
}
