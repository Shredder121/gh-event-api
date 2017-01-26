package com.github.shredder121.gh_event_api.handler.delete;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements DeleteHandler {

    @Override
    public void handle(DeletePayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(DeletePayload::getRefType, is("tag")),
                property(DeletePayload::getRef, is("simple-tag")),
                property(DeletePayload::getPusherType, is("user")),
                property(DeletePayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(DeletePayload::getOrganization, is(nullValue())),
                property(DeletePayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
