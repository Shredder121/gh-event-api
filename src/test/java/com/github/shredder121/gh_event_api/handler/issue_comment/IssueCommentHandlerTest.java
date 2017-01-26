package com.github.shredder121.gh_event_api.handler.issue_comment;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {IssueCommentHandlerTest.class, GHEventApiServer.class})
public class IssueCommentHandlerTest extends AbstractHandlerTest {

    public IssueCommentHandlerTest() {
        super("issue_comment");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
