package com.github.shredder121.gh_event_api.handler.commit_comment;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {CommitCommentHandlerTest.class, GHEventApiServer.class})
public class CommitCommentHandlerTest extends AbstractHandlerTest {

    public CommitCommentHandlerTest() {
        super("commit_comment");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
