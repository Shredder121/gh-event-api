package com.github.shredder121.gh_event_api.handler.release;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {ReleaseHandlerTest.class, GHEventApiServer.class})
public class ReleaseHandlerTest extends AbstractHandlerTest {

    public ReleaseHandlerTest() {
        super("release");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
