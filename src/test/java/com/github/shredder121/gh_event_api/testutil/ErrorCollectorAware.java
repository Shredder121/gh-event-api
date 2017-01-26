package com.github.shredder121.gh_event_api.testutil;

import org.junit.rules.ErrorCollector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;

public interface ErrorCollectorAware extends Aware {

    void setErrorCollector(ErrorCollector errorCollector) throws BeansException;
}
