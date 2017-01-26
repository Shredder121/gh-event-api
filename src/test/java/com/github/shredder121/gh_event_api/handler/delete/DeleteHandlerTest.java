package com.github.shredder121.gh_event_api.handler.delete;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {DeleteHandlerTest.class, GHEventApiServer.class})
public class DeleteHandlerTest extends AbstractHandlerTest {

    public DeleteHandlerTest() {
        super("delete");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
