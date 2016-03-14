package com.github.shredder121.gh_event_api.handler.watch;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.BAXTERTHEHACKER_PUBLIC_REPO;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements WatchHandler {

    @Override
    public void handle(WatchPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(WatchPayload::getAction, is("started")),
                property(WatchPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(WatchPayload::getOrganization, is(nullValue())),
                property(WatchPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
