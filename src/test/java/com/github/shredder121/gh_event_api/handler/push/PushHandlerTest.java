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
package com.github.shredder121.gh_event_api.handler.push;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.springframework.boot.test.*;
import org.springframework.context.annotation.Bean;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Queues;

@SpringApplicationConfiguration(classes = {PushHandlerTest.class, GHEventApiServer.class})
public class PushHandlerTest extends AbstractHandlerTest {

    private static final BlockingQueue<String> exchange = Queues.newSynchronousQueue();

    public PushHandlerTest() {
        super("push");
    }

    @Override
    protected Map<String, Object> getBody() {
        return ImmutableMap.of(
                "head", "1ab334",
                "ref", "test-branch",
                "before", "first",
                "after", "second",
                "commits", Collections.emptyList());
    }

    @Override
    public void doTest() throws InterruptedException {
        String output = exchange.take();
        assertEquals("1ab334", output);
    }

    @Bean
    public PushHandler handlerBean() {
        return payload -> {
            try {
                exchange.put(payload.getHead());
            } catch (InterruptedException ex) {
                throw Throwables.propagate(ex);
            }
        };
    }
}
