package com.github.shredder121.gh_event_api.handler.deployment_status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;

@SpringBootTest(classes = {DeploymentStatusHandlerTest.class, GHEventApiServer.class})
public class DeploymentStatusHandlerTest extends AbstractHandlerTest {

    public DeploymentStatusHandlerTest() {
        super("deployment_status");
    }

    @Bean
    public TestHandler handlerBean() {
        return new TestHandler();
    }
}
