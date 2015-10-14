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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.create.CreateHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"secret=secret", "spring.main.show-banner=false"})
@SpringApplicationConfiguration(classes = {HmacBehaviorTest.class, GHEventApiServer.class})
@DirtiesContext
public class HmacBehaviorTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testHmacIncorrect() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-GitHub-Event", "create");
        headers.add("X-Hub-Signature", "bogus");
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<?> response = restTemplate.postForEntity("http://127.0.0.1:8080", new HttpEntity<>("{}", headers), null);
        assertEquals("Known party, but Hmac incorrect", HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testHmacMissing() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-GitHub-Event", "create");
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<?> response = restTemplate.postForEntity("http://127.0.0.1:8080", new HttpEntity<>("{}", headers), null);
        assertEquals("Unknown party, return 404", HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testHmacOkay() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-GitHub-Event", "create");
        headers.add("X-Hub-Signature", "sha1=5d61605c3feea9799210ddcb71307d4ba264225f");
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<?> response = restTemplate.postForEntity("http://127.0.0.1:8080", new HttpEntity<>("{}", headers), null);
        assertEquals("Hmac fine, return 200", HttpStatus.OK, response.getStatusCode());
    }

    @Bean
    public CreateHandler handlerBean() {
        return payload -> {
        };
    }
}
