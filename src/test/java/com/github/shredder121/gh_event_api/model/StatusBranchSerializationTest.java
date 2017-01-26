package com.github.shredder121.gh_event_api.model;

import static org.hamcrest.Matchers.is;

public class StatusBranchSerializationTest extends AbstractSerializationTest<StatusBranch> {

    static String SAMPLE_STATUS_BRANCH
            = "{\n"
            + "  \"name\": \"master\",\n"
            + "  \"commit\": {\n"
            + "    \"sha\": \"9049f1265b7d61be4a8904a9a27120d2064dab3b\",\n"
            + "    \"url\": \"https://api.github.com/repos/baxterthehacker/public-repo/commits/9049f1265b7d61be4a8904a9a27120d2064dab3b\"\n"
            + "  }\n"
            + "}";

    public StatusBranchSerializationTest() {
        super(StatusBranch.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_STATUS_BRANCH;
    }

    @Override
    protected void checkDeserialized(StatusBranch branch) {
        errorCollector.checkThat(branch.getName(), is("master"));
    }
}
