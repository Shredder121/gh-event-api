package com.github.shredder121.gh_event_api.handler.deployment;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringApplicationConfiguration(classes = {DeploymentHandlerTest.class, GHEventApiServer.class})
public class DeploymentHandlerTest extends AbstractHandlerTest {

    public DeploymentHandlerTest() {
        super("deployment");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
