package com.github.shredder121.gh_event_api.handler.gollum;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {GollumHandlerTest.class, GHEventApiServer.class})
public class GollumHandlerTest extends AbstractHandlerTest {

    public GollumHandlerTest() {
        super("gollum");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
