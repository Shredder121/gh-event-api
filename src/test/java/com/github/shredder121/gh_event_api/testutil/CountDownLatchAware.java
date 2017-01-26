package com.github.shredder121.gh_event_api.testutil;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;

public interface CountDownLatchAware extends Aware {

    void setCountDownLatch(CountDownLatch countDownLatch) throws BeansException;
}
