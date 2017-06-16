package com.github.shredder121.gh_event_api.handler.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {StatusHandlerTest.class, GHEventApiServer.class})
public class StatusHandlerTest extends AbstractHandlerTest {

    public StatusHandlerTest() {
        super("status");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
