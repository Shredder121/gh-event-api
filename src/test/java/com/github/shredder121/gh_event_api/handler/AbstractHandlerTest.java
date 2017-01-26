package com.github.shredder121.gh_event_api.handler;

import static com.github.shredder121.gh_event_api.TestConstants.DEVELOPER_GITHUB_COM_REVISION;
import static com.github.shredder121.gh_event_api.TestConstants.HMACS;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.FileUtils;
import org.junit.After;
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
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shredder121.gh_event_api.testutil.RawGitOkHttpConnector;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.jayway.restassured.RestAssured;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import lombok.experimental.NonFinal;

@DirtiesContext
@WebIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractHandlerTest {

    static Map<String, GHContent> eventPayloadMap;

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
    @NonFinal Environment env;

    protected AbstractHandlerTest(String event) {
        this.event = event;
        /* This will be the HMAC as reported by https://www.freeformatter.com/hmac-generator.html */
        this.hmac = HMACS.get(event);
    }

    @Before
    public final void prepareTest() {
        handlerBean.setCountDownLatch(completion);
        handlerBean.setErrorCollector(errorCollector);

        RestAssured.port = env.getRequiredProperty("local.server.port", int.class);
    }

    @After
    public void resetRestAssured() {
        RestAssured.reset();
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
