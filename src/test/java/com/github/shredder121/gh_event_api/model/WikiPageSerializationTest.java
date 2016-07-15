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
package com.github.shredder121.gh_event_api.model;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.*;

public class WikiPageSerializationTest extends AbstractSerializationTest<WikiPage> {

    static String SAMPLE_WIKI_PAGE
            = "{\n"
            + "  \"page_name\": \"Home\",\n"
            + "  \"title\": \"Home\",\n"
            + "  \"summary\": null,\n"
            + "  \"action\": \"created\",\n"
            + "  \"sha\": \"dfc70625d16d47316ee20283240740f76cf3d195\",\n"
            + "  \"html_url\": \"https://github.com/Shredder121-me/yummy-octo-turtle/wiki/Home\"\n"
            + "}";

    public WikiPageSerializationTest() {
        super(WikiPage.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_WIKI_PAGE;
    }

    @Override
    protected void checkDeserialized(WikiPage wikiPage) {
        errorCollector.checkThat(wikiPage, allOf(
                property(WikiPage::getPageName, is("Home")),
                property(WikiPage::getTitle, is("Home")),
                property(WikiPage::getSummary, is(nullValue())),
                property(WikiPage::getAction, is("created")),
                property(WikiPage::getSha, is("dfc70625d16d47316ee20283240740f76cf3d195")),
                property(WikiPage::getHtmlUrl, is("https://github.com/Shredder121-me/yummy-octo-turtle/wiki/Home"))
        ));
    }
}
