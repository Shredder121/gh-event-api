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

import static org.hamcrest.Matchers.is;

public class LinkSerializationTest extends AbstractSerializationTest<Link> {

    static String SAMPLE_LINK
            = "{\n"
            + "  \"href\": \"https://github.com/baxterthehacker/public-repo/pull/1\"\n"
            + "}";

    public LinkSerializationTest() {
        super(Link.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_LINK;
    }

    @Override
    protected void checkDeserialized(Link link) {
        errorCollector.checkThat(link.getHref(), is("https://github.com/baxterthehacker/public-repo/pull/1"));
    }
}
