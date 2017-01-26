package com.github.shredder121.gh_event_api.handler.Public;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {PublicHandlerTest.class, GHEventApiServer.class})
public class PublicHandlerTest extends AbstractHandlerTest {

    public PublicHandlerTest() {
        super("public");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
