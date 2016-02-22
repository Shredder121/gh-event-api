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
package com.github.shredder121.gh_event_api.handler.gollum;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;
import com.github.shredder121.gh_event_api.model.WikiPage;

class TestHandler extends AbstractTestHandlerBean implements GollumHandler {

    @Override
    public void handle(GollumPayload payload) {
        errorCollector.checkThat(payload.getPages(), hasItem(
                allOf(
                        property(WikiPage::getAction, is("created")),
                        property(WikiPage::getPageName, is("Home")),
                        property(WikiPage::getSummary, is(nullValue())),
                        property(WikiPage::getSha, is("91ea1bd42aa2ba166b86e8aefe049e9837214e67")),
                        property(WikiPage::getHtmlUrl, is("https://github.com/baxterthehacker/public-repo/wiki/Home"))
                )));

        errorCollector.checkThat(payload.getRepository(), allOf(
                property(Repository::getFullName, is("baxterthehacker/public-repo")),
                property(Repository::getName, is("public-repo"))
        ));

        errorCollector.checkThat(payload.getOrganization(), is(nullValue()));

        errorCollector.checkThat(payload.getSender(), allOf(
                property(User::getId, is(2988)),
                property(User::getLogin, is("jasonrudolph"))
        ));

        countDownLatch.countDown();
    }
}
