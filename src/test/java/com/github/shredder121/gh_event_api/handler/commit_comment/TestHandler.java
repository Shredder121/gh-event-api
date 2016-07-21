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
package com.github.shredder121.gh_event_api.handler.commit_comment;

import static com.github.shredder121.gh_event_api.testutil.DateTimeHelpers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import java.time.ZonedDateTime;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Comment;

class TestHandler extends AbstractTestHandlerBean implements CommitCommentHandler {

    ZonedDateTime commentTime = dt("2015-05-05", "23:40:29");

    @Override
    public void handle(CommitCommentPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(CommitCommentPayload::getAction, is("created")),
                property(CommitCommentPayload::getComment, commentMatchers()),
                property(CommitCommentPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(CommitCommentPayload::getOrganization, is(nullValue())),
                property(CommitCommentPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    private Matcher<Comment> commentMatchers() {
        return allOf(asList(
                property(Comment::getId, is(11056394)),
                property(Comment::getCommitId, is("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(Comment::getPath, is(nullValue())),
                property(Comment::getPosition, is(nullValue())),
                property(Comment::getBody, is("This is a really good change! :+1:")),
                property(Comment::getUrl, containsString("11056394")),
                property(Comment::getHtmlUrl, containsString("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(Comment::getCreatedAt, is(commentTime)),
                property(Comment::getUpdatedAt, is(commentTime))
        ));
    }
}
