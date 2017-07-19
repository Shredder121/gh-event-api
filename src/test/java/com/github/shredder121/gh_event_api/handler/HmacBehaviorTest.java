package com.github.shredder121.gh_event_api.handler;

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.github.shredder121.gh_event_api.TestConstants.MINIMIZER;
import static com.github.shredder121.gh_event_api.filter.HeaderNames.GITHUB_EVENT_HEADER;
import static com.github.shredder121.gh_event_api.filter.HeaderNames.GITHUB_SIGNATURE_HEADER;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shredder121.gh_event_api.GHEventApiServer;
import com.github.shredder121.gh_event_api.filter.GithubMACChecker;
import com.github.shredder121.gh_event_api.filter.GithubMDCInsertingServletFilter;
import com.github.shredder121.gh_event_api.handler.create.CreateHandler;
import com.github.shredder121.gh_event_api.testutil.HmacTest;
import com.google.common.collect.ImmutableMap;

import lombok.experimental.NonFinal;

@HmacTest
@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HmacBehaviorTest.class, GHEventApiServer.class})
public class HmacBehaviorTest {

    @Autowired
    @NonFinal ObjectMapper objectMapper;

    @Autowired
    @NonFinal MockMvc mvc;

    @Before
    public void configureObjectMapper() {
        objectMapper.disable(FAIL_ON_EMPTY_BEANS);
    }

    @Bean
    public MockMvc setupMockMvc(WebApplicationContext context, GithubMDCInsertingServletFilter mdcFilter, GithubMACChecker macFilter) {

        return MockMvcBuilders.webAppContextSetup(context)
                .addFilters(mdcFilter, macFilter)
                .build();
    }

    @Test
    public void testNoFilter() throws Exception {
        mvc.perform(post("/")
                //no additional parameters/headers
        ).andExpect(status().isOk()).andExpect(content().string("handled"));
    }

    @Test
    public void testHmacIncorrect() throws Exception {
        mvc.perform(post("/")
                .header(GITHUB_EVENT_HEADER, "create")
                .header(GITHUB_SIGNATURE_HEADER, "bogus")
                .content(getContent()).contentType(APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void testHmacMissing() throws Exception {
        mvc.perform(post("/")
                .header(GITHUB_EVENT_HEADER, "create")
                .content(getContent()).contentType(APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testHmacOkay() throws Exception {
        mvc.perform(post("/")
                .header(GITHUB_EVENT_HEADER, "create")
                .header(GITHUB_SIGNATURE_HEADER, "sha1=807810001d379cceefed1898c5cacee2b0462b6c")
                .content(getContent()).contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    private String getContent() throws JsonProcessingException {
        ImmutableMap<String, Object> map = ImmutableMap.<String, Object>builder()
                .put("ref_type", "tag")
                .put("ref", "0.1")
                .put("master_branch", "master")
                .put("description", "")
                .put("pusher_type", "user")
                .put("repository", new Object())
                .put("sender", new Object())
                .build();
        return objectMapper.writer(MINIMIZER)
                .writeValueAsString(map);
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
