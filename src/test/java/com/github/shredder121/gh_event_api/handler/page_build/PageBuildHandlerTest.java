package com.github.shredder121.gh_event_api.handler.page_build;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {PageBuildHandlerTest.class, GHEventApiServer.class})
public class PageBuildHandlerTest extends AbstractHandlerTest {

    public PageBuildHandlerTest() {
        super("page_build");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
