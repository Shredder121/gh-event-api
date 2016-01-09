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

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.handler.create.CreateHandler;
import com.google.common.collect.ImmutableMap;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"secret=secret", "spring.main.show-banner=false"})
@SpringApplicationConfiguration(classes = {HmacBehaviorTest.class, GHEventApiServer.class})
@DirtiesContext
public class HmacBehaviorTest {

    @Test
    public void testHmacIncorrect() {
        given().headers(
                "X-GitHub-Event", "create",
                "X-Hub-Signature", "bogus")
        .and().body(getBody()).with().contentType(JSON)
        .expect().statusCode(HttpStatus.FORBIDDEN.value())
        .when().post();
    }

    @Test
    public void testHmacMissing() {
        given().headers("X-GitHub-Event", "create")
        .and().body(getBody()).with().contentType(JSON)
        .expect().statusCode(HttpStatus.NOT_FOUND.value())
        .when().post();
    }

    @Test
    public void testHmacOkay() {
        given().headers(
                "X-GitHub-Event", "create",
                "X-Hub-Signature", "sha1=95932ea97b74d2dac6d67ae73bf1af6f43ce6db3")
        .and().body(getBody()).with().contentType(JSON)
        .expect().statusCode(HttpStatus.OK.value())
        .when().post();
    }

    private static Map<String, Object> getBody() {
        return ImmutableMap.of(
                "ref_type", "tag",
                "ref", "0.1",
                "master_branch", "master",
                "description", "");
    }

    @Bean
    public CreateHandler handlerBean() {
        return payload -> {
        };
    }
}
