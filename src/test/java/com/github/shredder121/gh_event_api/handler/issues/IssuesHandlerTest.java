package com.github.shredder121.gh_event_api.handler.issues;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {IssuesHandlerTest.class, GHEventApiServer.class})
public class IssuesHandlerTest extends AbstractHandlerTest {

    public IssuesHandlerTest() {
        super("issues");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
