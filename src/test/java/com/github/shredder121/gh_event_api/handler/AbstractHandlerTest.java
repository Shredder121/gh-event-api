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

import static com.github.shredder121.gh_event_api.TestConstants.*;
import static com.github.shredder121.gh_event_api.filter.HeaderNames.GITHUB_EVENT_HEADER;
import static com.github.shredder121.gh_event_api.filter.HeaderNames.GITHUB_SIGNATURE_HEADER;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.FileUtils;
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
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shredder121.gh_event_api.filter.GithubMACChecker;
import com.github.shredder121.gh_event_api.filter.GithubMDCInsertingServletFilter;
import com.github.shredder121.gh_event_api.testutil.RawGitOkHttpConnector;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import lombok.experimental.NonFinal;

@DirtiesContext
@RunWith(SpringRunner.class)
public abstract class AbstractHandlerTest {

    private static final Map<String, GHContent> eventPayloadMap;

    static {
        try {
            GitHub github = getGitHub();

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

    private static GitHub getGitHub() throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.setCache(new Cache(Paths.get(".", ".cache").toFile(), FileUtils.ONE_MB * 10));
        return new GitHubBuilder()
                .withConnector(new RawGitOkHttpConnector(new OkUrlFactory(client)))
                .build();
    }

    private static String fileNameWithoutPayloadJsonSuffix(GHContent content) {
        String fileName = content.getName();
        return fileName.substring(0, fileName.indexOf('.'));
    }

    String event;
    String hmac;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public TestRule timeout = new DisableOnDebug(Timeout.seconds(30));

    protected CountDownLatch completion = new CountDownLatch(1);

    @Autowired
    @NonFinal AbstractTestHandlerBean handlerBean;

    @Autowired
    @NonFinal MockMvc mvc;

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

    @Bean
    public MockMvc setupMockMvc(WebApplicationContext context,
            GithubMDCInsertingServletFilter mdcfilter, GithubMACChecker macFilter) {

        return MockMvcBuilders.webAppContextSetup(context)
                .addFilters(mdcfilter, macFilter)
                .build();
    }

    @Test
    public void test() throws Exception {
        mvc.perform(post("/")
                .header(GITHUB_EVENT_HEADER, event)
                .header(GITHUB_SIGNATURE_HEADER, "sha1=" + hmac)
                .content(getContent()).contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());

        completion.await();
    }

    private String getContent() {
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
        return mapper.writer(MINIMIZER).writeValueAsString(content);
    }
}
