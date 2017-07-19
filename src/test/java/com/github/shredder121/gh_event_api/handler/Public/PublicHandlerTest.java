package com.github.shredder121.gh_event_api.handler.Public;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {PublicHandlerTest.class, GHEventApiServer.class})
public class PublicHandlerTest extends AbstractHandlerTest {

    public PublicHandlerTest() {
        super("public");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
