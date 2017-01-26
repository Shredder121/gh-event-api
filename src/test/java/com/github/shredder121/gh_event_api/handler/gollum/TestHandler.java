package com.github.shredder121.gh_event_api.handler.gollum;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.JasonRudolph.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.WikiPage;

class TestHandler extends AbstractTestHandlerBean implements GollumHandler {

    @Override
    public void handle(GollumPayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(GollumPayload::getPages, pagesMatchers()),
                property(GollumPayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(GollumPayload::getOrganization, is(nullValue())),
                property(GollumPayload::getSender, is(JASONRUDOLPH))
        )));
        countDownLatch.countDown();
    }

    public Matcher<Iterable<? extends WikiPage>> pagesMatchers() {
        return contains(
                allOf(asList(
                        property(WikiPage::getAction, is("created")),
                        property(WikiPage::getPageName, is("Home")),
                        property(WikiPage::getSummary, is(nullValue())),
                        property(WikiPage::getSha, is("91ea1bd42aa2ba166b86e8aefe049e9837214e67")),
                        property(WikiPage::getHtmlUrl, is("https://github.com/baxterthehacker/public-repo/wiki/Home"))
                ))
        );
    }
}
