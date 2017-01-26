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
