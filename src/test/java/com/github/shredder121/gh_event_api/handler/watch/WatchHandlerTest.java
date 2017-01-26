package com.github.shredder121.gh_event_api.handler.watch;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {WatchHandlerTest.class, GHEventApiServer.class})
public class WatchHandlerTest extends AbstractHandlerTest {

    public WatchHandlerTest() {
        super("watch");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
