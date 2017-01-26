package com.github.shredder121.gh_event_api.handler.status;

import static com.github.shredder121.gh_event_api.testutil.DateTimeHelpers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.GitCommit;
import com.github.shredder121.gh_event_api.model.GitCommit.UserData;
import com.github.shredder121.gh_event_api.model.StatusBranch;
import com.github.shredder121.gh_event_api.model.StatusCommit;

class TestHandler extends AbstractTestHandlerBean implements StatusHandler {

    @Override
    public void handle(StatusPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(StatusPayload::getContext, is("default")),
                property(StatusPayload::getState, is("success")),
                property(StatusPayload::getSha, is("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(StatusPayload::getName, is("baxterthehacker/public-repo")),
                property(StatusPayload::getDescription, is(nullValue())),
                property(StatusPayload::getTargetUrl, is(nullValue())),
                property(StatusPayload::getBranches, branchesMatchers()),
                property(StatusPayload::getCommit, commitMatchers()),
                property(StatusPayload::getUpdatedAt, is(payload.getCreatedAt())),
                property(StatusPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(StatusPayload::getOrganization, is(nullValue())),
                property(StatusPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    public Matcher<Iterable<? extends StatusBranch>> branchesMatchers() {
        return contains(asList(
                allOf(asList(
                        property(StatusBranch::getName, is("master")),
                        property(StatusBranch::getCommit, allOf(asList(
                                property(StatusCommit::getSha, is("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                                property(StatusCommit::getUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/commits/9049f1265b7d61be4a8904a9a27120d2064dab3b"))
                        )))
                )),
                allOf(asList(
                        property(StatusBranch::getName, is("changes")),
                        property(StatusBranch::getCommit, allOf(asList(
                                property(StatusCommit::getSha, is("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c")),
                                property(StatusCommit::getUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/commits/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c"))
                        )))
                )),
                allOf(asList(
                        property(StatusBranch::getName, is("gh-pages")),
                        property(StatusBranch::getCommit, allOf(asList(
                                property(StatusCommit::getSha, is("b11bb7545ac14abafc6191a0481b0d961e7793c6")),
                                property(StatusCommit::getUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/commits/b11bb7545ac14abafc6191a0481b0d961e7793c6"))
                        )))
                ))
        ));
    }

    public Matcher<StatusCommit> commitMatchers() {
        return allOf(asList(
                property(StatusCommit::getSha, is("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(StatusCommit::getUrl, containsString("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(StatusCommit::getHtmlUrl, containsString("9049f1265b7d61be4a8904a9a27120d2064dab3b")),
                property(StatusCommit::getCommit, allOf(asList(
                        property(GitCommit::getMessage, is("Initial commit")),
                        property(GitCommit::getAuthor, committerAndAuthorMatchers()),
                        property(GitCommit::getCommitter, committerAndAuthorMatchers())
                )))
        ));
    }

    public Matcher<UserData> committerAndAuthorMatchers() {
        return allOf(
                property(UserData::getName, is("baxterthehacker")),
                property(UserData::getEmail, is("baxterthehacker@users.noreply.github.com")),
                property(UserData::getDate, is(dt("2015-05-05", "23:40:12")))
        );
    }

}
