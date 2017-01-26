package com.github.shredder121.gh_event_api.model;

import static org.hamcrest.Matchers.is;

public class StatusCommitSerializationTest extends AbstractSerializationTest<StatusCommit> {

    static String SAMPLE_STATUS_COMMIT
            = "{\n"
            + "  \"sha\": \"9049f1265b7d61be4a8904a9a27120d2064dab3b\",\n"
            + "  \"url\": \"https://api.github.com/repos/baxterthehacker/public-repo/commits/9049f1265b7d61be4a8904a9a27120d2064dab3b\"\n"
            + "}";

    public StatusCommitSerializationTest() {
        super(StatusCommit.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_STATUS_COMMIT;
    }

    @Override
    protected void checkDeserialized(StatusCommit deserialized) {
        errorCollector.checkThat(deserialized.getSha(), is("9049f1265b7d61be4a8904a9a27120d2064dab3b"));
    }
}
