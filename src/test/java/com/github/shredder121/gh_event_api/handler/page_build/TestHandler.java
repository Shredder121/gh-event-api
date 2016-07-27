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
package com.github.shredder121.gh_event_api.handler.page_build;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.PageBuild;

class TestHandler extends AbstractTestHandlerBean implements PageBuildHandler {

    @Override
    public void handle(PageBuildPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(PageBuildPayload::getId, is(15995382)),
                property(PageBuildPayload::getBuild, pageBuildMatchers()),
                property(PageBuildPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(PageBuildPayload::getOrganization, is(nullValue())),
                property(PageBuildPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    public Matcher<PageBuild> pageBuildMatchers() {
        return allOf(asList(
                property(PageBuild::getUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/pages/builds/15995382")),
                property(PageBuild::getStatus, is("built")),
                property(PageBuild::getError, hasProperty("message", nullValue())),
                property(PageBuild::getPusher, is(BAXTERTHEHACKER)),
                property(PageBuild::getCommit, is("053b99542c83021d6b202d1a1f5ecd5ef7084e55")),
                property(PageBuild::getDuration, is(3790L)),
                property(PageBuild::getCreatedAt, hasToString(startsWith("2015-05-05T23:40:13Z"))),
                property(PageBuild::getUpdatedAt, hasToString(startsWith("2015-05-05T23:40:17Z")))
        ));
    }
}
