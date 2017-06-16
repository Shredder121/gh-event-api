package com.github.shredder121.gh_event_api.handler.team_add;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {TeamAddHandlerTest.class, GHEventApiServer.class})
public class TeamAddHandlerTest extends AbstractHandlerTest {

    public TeamAddHandlerTest() {
        super("team_add");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
