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
package com.github.shredder121.gh_event_api.handler.status;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static com.google.common.collect.Iterables.transform;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collection;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.GitCommit;
import com.github.shredder121.gh_event_api.model.GitCommit.UserData;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.StatusBranch;
import com.github.shredder121.gh_event_api.model.StatusCommit;

class TestHandler extends AbstractTestHandlerBean implements StatusHandler {

    @Override
    public void handle(StatusPayload payload) {
        errorCollector.checkThat(payload.getContext(), is("default"));
        errorCollector.checkThat(payload.getState(), is("success"));
        errorCollector.checkThat(payload.getSha(), is("9049f1265b7d61be4a8904a9a27120d2064dab3b"));
        errorCollector.checkThat(payload.getName(), is("baxterthehacker/public-repo"));
        errorCollector.checkThat(payload.getDescription(), is(nullValue()));
        errorCollector.checkThat(payload.getTargetUrl(), is(nullValue()));

        Collection<StatusBranch> branches = payload.getBranches();
        errorCollector.checkThat(transform(branches, StatusBranch::getName),
                everyItem(either(is("master")).or(is("changes")).or(is("gh-pages"))));

        StatusCommit commit = payload.getCommit();
        errorCollector.checkThat(commit.getSha(), is("9049f1265b7d61be4a8904a9a27120d2064dab3b"));
        errorCollector.checkThat(commit.getUrl(), containsString("9049f1265b7d61be4a8904a9a27120d2064dab3b"));
        errorCollector.checkThat(commit.getHtmlUrl(), containsString("9049f1265b7d61be4a8904a9a27120d2064dab3b"));
        errorCollector.checkThat(commit.getCommit(), allOf(
                property(GitCommit::getMessage, is("Initial commit")),
                property(GitCommit::getAuthor, allOf(
                        property(UserData::getName, is("baxterthehacker")),
                        property(UserData::getEmail, is("baxterthehacker@users.noreply.github.com")),
                        property(UserData::getDate, is(LocalDateTime.parse("2015-05-05T23:40:12").atZone(ZoneId.ofOffset("GMT", ZoneOffset.UTC))))
                )),
                property(GitCommit::getCommitter, allOf(
                        property(UserData::getName, is("baxterthehacker")),
                        property(UserData::getEmail, is("baxterthehacker@users.noreply.github.com")),
                        property(UserData::getDate, is(LocalDateTime.parse("2015-05-05T23:40:12").atZone(ZoneId.ofOffset("GMT", ZoneOffset.UTC))))
                ))
        ));

        errorCollector.checkThat(payload.getUpdatedAt(), is(payload.getCreatedAt()));

        Repository repository = payload.getRepository();
        errorCollector.checkThat(repository.getName(), is("public-repo"));
        errorCollector.checkThat(repository.getFullName(), is("baxterthehacker/public-repo"));

        errorCollector.checkThat(payload.getOrganization(), is(nullValue()));

        errorCollector.checkThat(payload.getSender().getLogin(), is("baxterthehacker"));

        countDownLatch.countDown();
    }
}
