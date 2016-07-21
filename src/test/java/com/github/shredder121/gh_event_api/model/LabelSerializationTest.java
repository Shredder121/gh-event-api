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

public class LabelSerializationTest extends AbstractSerializationTest<Label> {

    static String SAMPLE_LABEL
            = "{\n"
            + "  \"url\": \"https://api.github.com/repos/Shredder121/gh-event-api/labels/in%20progress\",\n"
            + "  \"name\": \"in progress\",\n"
            + "  \"color\": \"ededed\"\n"
            + "}";

    public LabelSerializationTest() {
        super(Label.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_LABEL;
    }

    @Override
    protected void checkDeserialized(Label label) {
        errorCollector.checkThat(label.getName(), is("in progress"));
    }
}
