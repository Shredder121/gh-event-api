package com.github.shredder121.gh_event_api.handler.pull_request_review_comment;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {PullRequestReviewCommentHandlerTest.class, GHEventApiServer.class})
public class PullRequestReviewCommentHandlerTest extends AbstractHandlerTest {

    public PullRequestReviewCommentHandlerTest() {
        super("pull_request_review_comment");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
