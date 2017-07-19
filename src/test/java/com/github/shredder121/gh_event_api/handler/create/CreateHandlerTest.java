package com.github.shredder121.gh_event_api.handler.create;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {CreateHandlerTest.class, GHEventApiServer.class})
public class CreateHandlerTest extends AbstractHandlerTest {

    public CreateHandlerTest() {
        super("create");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
