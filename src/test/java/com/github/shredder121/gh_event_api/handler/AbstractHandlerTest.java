/*
 * Copyright 2016 Shredder121.
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

import static com.github.shredder121.gh_event_api.TestConstants.DEVELOPER_GITHUB_COM_REVISION;
import static com.github.shredder121.gh_event_api.TestConstants.HMACS;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"spring.main.show-banner=false"})
@DirtiesContext
public abstract class AbstractHandlerTest {

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

    private final String event;
    private final String hmac;

    @Rule
    public final ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public final TestRule timeout = new DisableOnDebug(Timeout.seconds(10));

    protected final CountDownLatch completion = new CountDownLatch(1);

    @Autowired
    private AbstractTestHandlerBean handlerBean;

    protected AbstractHandlerTest(String event) {
        this.event = event;
        /* This will be the HMAC as reported by https://www.freeformatter.com/hmac-generator.html */
        this.hmac = HMACS.get(event);
    }

    @Before
    public final void prepareTest() {
        handlerBean.setCountDownLatch(completion);
        handlerBean.setErrorCollector(errorCollector);
    }

    @Test
    public void test() throws InterruptedException {
        given().headers(
                "X-GitHub-Event", event,
                "X-Hub-Signature", "sha1=" + hmac)
        .and().body(getBody()).with().contentType(JSON)
        .expect().statusCode(HttpStatus.OK.value())
        .when().post();

        completion.await();
    }

    private String getBody() {
        GHContent payloadFile = eventPayloadMap.get(event);
        try (InputStream stream = payloadFile.read()) {
            return minimize(stream);
        } catch (IOException ex) {
            throw Throwables.propagate(ex);
        }
    }

    public String minimize(final InputStream stream) throws IOException {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
        Map<String, Object> content = mapper.reader().forType(Map.class).readValue(stream);
        return mapper.writer(new MinimalPrettyPrinter(null/*minimizes*/)).writeValueAsString(content);
    }
}
