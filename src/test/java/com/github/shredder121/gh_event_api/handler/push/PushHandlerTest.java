package com.github.shredder121.gh_event_api.handler.push;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {PushHandlerTest.class, GHEventApiServer.class})
public class PushHandlerTest extends AbstractHandlerTest {

    public PushHandlerTest() {
        super("push");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
