package com.github.shredder121.gh_event_api.handler.push;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.PushCommit;

class TestHandler extends AbstractTestHandlerBean implements PushHandler {

    @Override
    public void accept(PushPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(PushPayload::getRef, is("refs/heads/changes")),
                property(PushPayload::getBefore, is("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(PushPayload::getAfter, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                property(PushPayload::getCreated, is(false)),
                property(PushPayload::getDeleted, is(false)),
                property(PushPayload::getForced, is(false)),
                property(PushPayload::getCommits, commitsMatchers()),
                property(PushPayload::getHeadCommit, headCommitMatchers()),
                property(PushPayload::getSender, is(BAXTERTHEHACKER)),
                property(PushPayload::getOrganization, is(nullValue())),
                property(PushPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO))
        )));
        countDownLatch.countDown();
    }

    public Matcher<Iterable<? extends PushCommit>> commitsMatchers() {
        return contains(
                allOf(asList(
                        property(PushCommit::getId, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                        property(PushCommit::getTreeId, is("f9d2a07e9488b91af2641b26b9407fe22a451433")),
                        property(PushCommit::getMessage, is("Update README.md"))
                ))
        );
    }

    public Matcher<PushCommit> headCommitMatchers() {
        return allOf(asList(
                property(PushCommit::getId, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                property(PushCommit::getTreeId, is("f9d2a07e9488b91af2641b26b9407fe22a451433")),
                property(PushCommit::getMessage, is("Update README.md"))
        ));
    }
}
