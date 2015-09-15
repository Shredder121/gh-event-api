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
package com.github.shredder121.gh_event_api.handler;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"spring.main.show-banner=false"})
@DirtiesContext
public abstract class AbstractHandlerTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpEntity<Map<String, Object>> request;
    private final String event;

    public AbstractHandlerTest(String event) {
        this.event = event;
    }

    @Before
    public void prepareRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-GitHub-Event", event);
        request = new HttpEntity<>(getBody(), headers);
    }

    @Test
    public final void test() throws Exception {
        restTemplate.postForEntity("http://127.0.0.1:8080", request, null);
        doTest();
    }

    protected abstract Map<String, Object> getBody();

    protected abstract void doTest() throws Exception;
}
