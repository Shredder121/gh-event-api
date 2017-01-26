package com.github.shredder121.gh_event_api.handler;

import java.util.concurrent.CountDownLatch;

import org.junit.rules.ErrorCollector;

import com.github.shredder121.gh_event_api.testutil.CountDownLatchAware;
import com.github.shredder121.gh_event_api.testutil.ErrorCollectorAware;

import lombok.experimental.NonFinal;

public abstract class AbstractTestHandlerBean implements CountDownLatchAware, ErrorCollectorAware {

    @NonFinal protected CountDownLatch countDownLatch;
    @NonFinal protected ErrorCollector errorCollector;

    @Override
    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void setErrorCollector(ErrorCollector errorCollector) {
        this.errorCollector = errorCollector;
    }
}
