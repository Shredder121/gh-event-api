package com.github.shredder121.gh_event_api.handler.member;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {MemberHandlerTest.class, GHEventApiServer.class})
public class MemberHandlerTest extends AbstractHandlerTest {

    public MemberHandlerTest() {
        super("member");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
