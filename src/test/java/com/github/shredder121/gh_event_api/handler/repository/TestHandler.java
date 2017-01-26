package com.github.shredder121.gh_event_api.handler.repository;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterAndTheHackers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements RepositoryHandler {

    @Override
    public void handle(RepositoryPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(RepositoryPayload::getAction, is("created")),
                property(RepositoryPayload::getRepository, is(BAXTERANDTHEHACKERS_NEW_REPOSITORY)),
                property(RepositoryPayload::getOrganization, is(BAXTERANDTHEHACKERS_ORG)),
                property(RepositoryPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
