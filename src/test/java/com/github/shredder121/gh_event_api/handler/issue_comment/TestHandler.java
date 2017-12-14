package com.github.shredder121.gh_event_api.handler.issue_comment;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Comment;
import com.github.shredder121.gh_event_api.model.Issue;

class TestHandler extends AbstractTestHandlerBean implements IssueCommentHandler {

    @Override
    public void handle(IssueCommentPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(IssueCommentPayload::getAction, is("created")),
                property(IssueCommentPayload::getIssue, issueMatchers()),
                property(IssueCommentPayload::getComment, commentMMatchers()),
                property(IssueCommentPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(IssueCommentPayload::getOrganization, is(nullValue())),
                property(IssueCommentPayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    public Matcher<Issue> issueMatchers() {
        return allOf(asList(
                property(Issue::getId, is(73464126L)),
                property(Issue::getNumber, is(2)),
                property(Issue::getTitle, is("Spelling error in the README file")),
                property(Issue::getBody, is("It looks like you accidently spelled 'commit' with two 't's."))
        ));
    }

    public Matcher<Comment> commentMMatchers() {
        return allOf(asList(
                property(Comment::getId, is(99262140L)),
                property(Comment::getBody, is("You are totally right! I'll get this fixed right away."))
        ));
    }
}
