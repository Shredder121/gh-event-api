package com.github.shredder121.gh_event_api.handler.member;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.Octocat.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean implements MemberHandler {

    @Override
    public void accept(MemberPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(MemberPayload::getAction, is("added")),
                property(MemberPayload::getMember, is(OCTOCAT)),
                property(MemberPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(MemberPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }
}
