package com.github.shredder121.gh_event_api.handler.issues;

import static com.github.shredder121.gh_event_api.testutil.DateTimeHelpers.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Issue;

class TestHandler extends AbstractTestHandlerBean implements IssuesHandler {

    @Override
    public void handle(IssuesPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(IssuesPayload::getAction, is("opened")),
                property(IssuesPayload::getIssue, issueMatchers()),
                property(IssuesPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(IssuesPayload::getOrganization, is(nullValue())),
                property(IssuesPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    public Matcher<Issue> issueMatchers() {
        return allOf(asList(
                property(Issue::getId, is(73464126L)),
                property(Issue::getNumber, is(2)),
                property(Issue::getState, is("open")),
                property(Issue::getTitle, is("Spelling error in the README file")),
                property(Issue::getBody, is("It looks like you accidently spelled 'commit' with two 't's.")),
                property(Issue::getCreatedAt, is(dt("2015-05-05", "23:40:28"))),
                property(Issue::getUpdatedAt, is(dt("2015-05-05", "23:40:28")))
        ));
    }
}
