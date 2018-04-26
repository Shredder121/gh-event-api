package com.github.shredder121.gh_event_api.handler.Public;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements PublicHandler {

    @Override
    public void accept(PublicPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(PublicPayload::getRepository, BAXTERTHEHACKER_PUBLIC_REPO),
                property(PublicPayload::getOrganization, is(nullValue())),
                property(PublicPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
