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

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.create.CreateHandler;
import com.google.common.collect.ImmutableMap;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.internal.mapping.Jackson2Mapper;
import com.jayway.restassured.mapper.ObjectMapper;
import com.jayway.restassured.mapper.factory.DefaultJackson2ObjectMapperFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"secret=secret", "spring.main.show-banner=false"})
@SpringApplicationConfiguration(classes = {HmacBehaviorTest.class, GHEventApiServer.class})
@DirtiesContext
public class HmacBehaviorTest {

    private static final ObjectMapper restAssuredMapper = new Jackson2Mapper(
            (clazz, charset) -> new DefaultJackson2ObjectMapperFactory()
                    .create(clazz, charset)
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS));

    @Autowired
    private Environment env;

    @Before
    public void setupRestAssured() {
        RestAssured.port = env.getRequiredProperty("local.server.port", int.class);
    }

    @After
    public void resetRestAssured() {
        RestAssured.reset();
    }

    @Test
    public void testNoFilter() {
        given()
                //no additional parameters/headers
        .expect()
                .statusCode(HttpStatus.OK.value())
                .body(is("handled"));
    }

    @Test
    public void testHmacIncorrect() {
        given().headers(
                "X-GitHub-Event", "create",
                "X-Hub-Signature", "bogus")
        .and().body(getBody(), restAssuredMapper).with().contentType(JSON)
        .expect().statusCode(HttpStatus.NO_CONTENT.value())
        .when().post();
    }

    @Test
    public void testHmacMissing() {
        given().headers("X-GitHub-Event", "create")
        .and().body(getBody(), restAssuredMapper).with().contentType(JSON)
        .expect().statusCode(HttpStatus.NOT_FOUND.value())
        .when().post();
    }

    @Test
    public void testHmacOkay() {
        given().headers(
                "X-GitHub-Event", "create",
                "X-Hub-Signature", "sha1=807810001d379cceefed1898c5cacee2b0462b6c")
        .and().body(getBody(), restAssuredMapper).with().contentType(JSON)
        .expect().statusCode(HttpStatus.OK.value())
        .when().post();
    }

    private static Map<String, Object> getBody() {
        return ImmutableMap.<String, Object>builder()
                .put("ref_type", "tag")
                .put("ref", "0.1")
                .put("master_branch", "master")
                .put("description", "")
                .put("pusher_type", "user")
                .put("repository", new Object())
                .put("sender", new Object())
                .build();
    }

    @Bean
    public CreateHandler handlerBean() {
        return payload -> {
        };
    }

    @RestController
    public static class TestController {

        @RequestMapping(method = POST)
        public String handle() {
            return "handled";
        }
    }
}
