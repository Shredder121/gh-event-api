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
package com.github.shredder121.gh_event_api.handler.release;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER_PUBLIC_REPO;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Release;

class TestHandler extends AbstractTestHandlerBean implements ReleaseHandler {

    @Override
    public void handle(ReleasePayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(ReleasePayload::getAction, is("published")),
                property(ReleasePayload::getRelease, releaseMatchers()),
                property(ReleasePayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(ReleasePayload::getOrganization, is(nullValue())),
                property(ReleasePayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    private static Matcher<Release> releaseMatchers() {
        return allOf(
                property(Release::getId, is(1261438)),
                property(Release::getAssets, is(empty())),
                property(Release::getUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/releases/1261438")),
                property(Release::getAssetsUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/releases/1261438/assets"))
        );
    }
}
