package com.github.shredder121.gh_event_api.handler.ping;

import java.util.concurrent.CountDownLatch;

import com.github.shredder121.gh_event_api.handler.AbstractTestHandlerBean;

class TestHandler extends AbstractTestHandlerBean {

    @Override
    public void setCountDownLatch(CountDownLatch countDownLatch) {
        countDownLatch.countDown(); // immediate completion
    }
}
