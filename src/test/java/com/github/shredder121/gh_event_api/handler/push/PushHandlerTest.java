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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Queues;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {PushHandlerTest.class, GHEventApiServer.class})
@WebIntegrationTest
@DirtiesContext
public class PushHandlerTest {

    private final Map<String, Object> body = ImmutableMap.of(
            "head", "1ab334",
            "ref", "test-branch",
            "before", "first",
            "after", "second",
            "commits", Collections.emptyList());

    private static final BlockingQueue<String> exchange = Queues.newSynchronousQueue();

    protected final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void test() throws InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-GitHub-Event", "push");
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.postForEntity("http://127.0.0.1:8080", request, null);
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
