package com.github.shredder121.gh_event_api.handler.release;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.BaxterTheHacker.*;
import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;
import com.github.shredder121.gh_event_api.model.Release;

class TestHandler extends AbstractTestHandlerBean implements ReleaseHandler {

    @Override
    public void handle(ReleasePayload payload) {
        errorCollector.checkThat(payload, allOf(asList(
                property(ReleasePayload::getAction, is("published")),
                property(ReleasePayload::getRelease, releaseMatchers()),
                property(ReleasePayload::getRepository, is(BAXTERTHEHACKER_PUBLIC_REPO)),
                property(ReleasePayload::getOrganization, is(nullValue())),
                property(ReleasePayload::getSender, is(BAXTERTHEHACKER))
        )));
        countDownLatch.countDown();
    }

    public Matcher<Release> releaseMatchers() {
        return allOf(
                property(Release::getId, is(1261438)),
                property(Release::getAssets, is(empty())),
                property(Release::getUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/releases/1261438")),
                property(Release::getAssetsUrl, is("https://api.github.com/repos/baxterthehacker/public-repo/releases/1261438/assets"))
        );
    }
}
