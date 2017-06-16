package com.github.shredder121.gh_event_api.handler.membership;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {MembershipHandlerTest.class, GHEventApiServer.class})
public class MembershipHandlerTest extends AbstractHandlerTest {

    public MembershipHandlerTest() {
        super("membership");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
