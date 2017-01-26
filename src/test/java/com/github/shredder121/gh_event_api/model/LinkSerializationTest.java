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
