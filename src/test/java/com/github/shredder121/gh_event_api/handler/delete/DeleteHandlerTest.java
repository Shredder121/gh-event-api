package com.github.shredder121.gh_event_api.handler.delete;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {DeleteHandlerTest.class, GHEventApiServer.class})
public class DeleteHandlerTest extends AbstractHandlerTest {

    public DeleteHandlerTest() {
        super("delete");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
