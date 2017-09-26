package com.github.shredder121.gh_event_api.handler.fork;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterAndTheHackers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements ForkHandler {

    @Override
    public void accept(ForkPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(ForkPayload::getSender, is(BAXTERANDTHEHACKERS)),
                property(ForkPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(ForkPayload::getForkee, is(BAXTERANDTHEHACKERS_PUBLIC_REPO))
        )));
        countDownLatch.countDown();
    }
}
