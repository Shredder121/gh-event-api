package com.github.shredder121.gh_event_api.handler.ping;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {PingTest.class, GHEventApiServer.class})
public class PingTest extends AbstractHandlerTest {

    public PingTest() {
        super("ping");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
