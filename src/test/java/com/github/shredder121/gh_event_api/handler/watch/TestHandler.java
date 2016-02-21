package com.github.shredder121.gh_event_api.handler.watch;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.property;
import static org.hamcrest.Matchers.*;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Repository;

class TestHandler extends AbstractTestHandlerBean implements WatchHandler {

    @Override
    public void handle(WatchPayload payload) {
        errorCollector.checkThat(payload.getAction(), is("started"));

        errorCollector.checkThat(payload.getRepository(), allOf(
                property(Repository::getName, is("public-repo")),
                property(Repository::getFullName, is("baxterthehacker/public-repo"))
        ));

        errorCollector.checkThat(payload.getOrganization(), is(nullValue()));

        errorCollector.checkThat(payload.getSender().getLogin(), is("baxterthehacker"));

        countDownLatch.countDown();
    }
}
