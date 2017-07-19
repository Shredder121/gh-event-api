package com.github.shredder121.gh_event_api.handler.pull_request;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {PullRequestHandlerTest.class, GHEventApiServer.class})
public class PullRequestHandlerTest extends AbstractHandlerTest {

    public PullRequestHandlerTest() {
        super("pull_request");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
