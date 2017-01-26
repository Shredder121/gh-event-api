package com.github.shredder121.gh_event_api.handler.fork;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {ForkHandlerTest.class, GHEventApiServer.class})
public class ForkHandlerTest extends AbstractHandlerTest {

    public ForkHandlerTest() {
        super("fork");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
