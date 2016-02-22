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

import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Comment;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

class TestHandler extends AbstractTestHandlerBean implements CommitCommentHandler {

    @Override
    public void handle(CommitCommentPayload payload) {
        errorCollector.checkThat(payload.getAction(), is("created"));

        Comment comment = payload.getComment();
        errorCollector.checkThat(comment.getId(), is(11056394));
        errorCollector.checkThat("This is a comment on the overall commit", comment.getPath(), is(nullValue()));
        errorCollector.checkThat("This is a comment on the overall commit", comment.getPosition(), is(nullValue()));
        errorCollector.checkThat(comment.getUrl(), containsString(String.valueOf(comment.getId())));
        errorCollector.checkThat(comment.getHtmlUrl(), containsString(comment.getCommitId()));
        errorCollector.checkThat(comment.getBody(), is("This is a really good change! :+1:"));

        ZonedDateTime commentTime = LocalDateTime.parse("2015-05-05T23:40:29").atZone(ZoneId.ofOffset("GMT", ZoneOffset.UTC));
        errorCollector.checkThat(comment.getCreatedAt(), is(commentTime));
        errorCollector.checkThat(comment.getUpdatedAt(), is(commentTime));

        Repository repository = payload.getRepository();
        errorCollector.checkThat(repository.getFullName(), is("baxterthehacker/public-repo"));
        errorCollector.checkThat(repository.getName(), is("public-repo"));

        errorCollector.checkThat(payload.getOrganization(), is(nullValue()));

        User sender = payload.getSender();
        errorCollector.checkThat(sender.getLogin(), is("baxterthehacker"));

        countDownLatch.countDown();
    }
}
