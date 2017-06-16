package com.github.shredder121.gh_event_api.handler.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {RepositoryHandlerTest.class, GHEventApiServer.class})
public class RepositoryHandlerTest extends AbstractHandlerTest {

    public RepositoryHandlerTest() {
        super("repository");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
