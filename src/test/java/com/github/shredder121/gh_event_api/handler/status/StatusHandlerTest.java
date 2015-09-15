/*
 * Copyright 2015 Shredder121.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.shredder121.gh_event_api.handler.status;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.springframework.boot.test.*;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.AbstractHandlerTest;
import com.github.shredder121.gh_event_api.model.Repository;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Queues;

@SpringApplicationConfiguration(classes = {StatusHandlerTest.class, GHEventApiServer.class})
public class StatusHandlerTest extends AbstractHandlerTest {

    private static final BlockingQueue<String> exchange = Queues.newSynchronousQueue();

    public StatusHandlerTest() {
        super("status");
    }

    @Override
    protected Map<String, Object> getBody() {
        return ImmutableMap.<String, Object>builder()
                .put("sha", "1ab334")
                .put("name", "anything")
                .put("context", "test")
                .put("state", "pending")
                .put("branches", Collections.emptyList())
                .put("repository", new Repository("something", null))
                .build();
    }

    @Override
    public void doTest() throws InterruptedException {
        String output = exchange.take();
        assertEquals("pending", output);
    }

    @Bean
    public StatusHandler handlerBean() {
        return payload -> {
            try {
                exchange.put(payload.getState());
            } catch (InterruptedException ex) {
                throw Throwables.propagate(ex);
            }
        };
    }
}
