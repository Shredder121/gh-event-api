package com.github.shredder121.gh_event_api.handler.create;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements CreateHandler {

    @Override
    public void accept(CreatePayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(CreatePayload::getDescription, is("")),
                property(CreatePayload::getRefType, is("tag")),
                property(CreatePayload::getRef, is("0.0.1")),
                property(CreatePayload::getMasterBranch, is("master")),
                property(CreatePayload::getPusherType, is("user")),
                property(CreatePayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(CreatePayload::getOrganization, is(nullValue())),
                property(CreatePayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
