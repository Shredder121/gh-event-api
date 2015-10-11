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

import static java.util.concurrent.TimeUnit.MINUTES;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.IOUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.kohsuke.github.*;
import org.springframework.boot.test.*;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"spring.main.show-banner=false"})
@DirtiesContext
public abstract class AbstractHandlerTest {

    private static final String DEVELOPER_GITHUB_COM_REVISION = "f5713bd67e1885cee9f3c6e41f03386792c5851c";
    private static final Map<String, GHContent> eventPayloadMap;

    static {
        try {
            GitHub github;
            try {
                github = GitHub.connect();
            } catch (IOException noAuthorizationDetails) {
                // This is fine, and still gives you 60 requests per hour, so one-off test runs still work
                github = GitHub.connectAnonymously();
            }

            List<GHContent> directoryContent = github.getRepository("github/developer.github.com")
                    .getDirectoryContent("lib/webhooks", DEVELOPER_GITHUB_COM_REVISION);

            // Map the event name (fileNameWithoutPayloadJsonSuffix) to the event's payload.json file
            //             push/pull_request/* -> lib/webhooks/*.payload.json
            eventPayloadMap = Maps.uniqueIndex(
                    directoryContent, AbstractHandlerTest::fileNameWithoutPayloadJsonSuffix);
        } catch (IOException ex) {
            throw Throwables.propagate(ex);
        }
    }

    private static String fileNameWithoutPayloadJsonSuffix(GHContent content) {
        String fileName = content.getName();
        return fileName.substring(0, fileName.indexOf('.'));
    }

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpEntity<String> request;
    private final String event;

    @ClassRule
    public static final ErrorCollector errorCollector = new ErrorCollector();

    protected static CountDownLatch completion;

    public AbstractHandlerTest(String event) {
        this.event = event;
    }

    @Before
    public final void prepareRequest() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-GitHub-Event", event);
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<>(getBody(), headers);
    }

    @Test
    public void test() throws Exception {
        completion = new CountDownLatch(1);
        restTemplate.postForEntity("http://127.0.0.1:8080", request, null);
        awaitCompletion();
    }

    public void awaitCompletion() throws InterruptedException {
        if (!completion.await(1, MINUTES)) {
            fail("Timeout during execution");
        }
    }

    private String getBody() throws IOException {
        GHContent payloadFile = eventPayloadMap.get(event);
        try (InputStream stream = payloadFile.read()) {
            return IOUtils.toString(stream);
        }
    }
}
