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

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.ImmutableSet;

/**
 * The class with all common logic for dispatching incoming events.
 *
 * @author Shredder121
 * @param <H> the handler type of the concrete controller
 * @param <P> the payload type of the concrete controller
 */
public abstract class AbstractEndpointController<H, P> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final TaskExecutor executor = new TaskExecutorAdapter(ForkJoinPool.commonPool());
    private final Collection<? extends H> handlers;

    protected AbstractEndpointController(Collection<? extends H> beans) {
        this.handlers = ImmutableSet.copyOf(beans);
    }

    /**
     * Handle the incoming payload.
     *
     * <p>
     * Upon receipt of a payload, the specialized payload
     * {@link com.github.shredder121.gh_event_api.handler handlers} are called that are registered for that particular event.
     * </p>
     *
     * @param payload the payload received
     */
    @RequestMapping(method = POST)
    public void handle(@Valid @RequestBody P payload) {
        logger.debug("{} handlers", handlers.size());
        for (H handler : handlers) {
            executor.execute(runnableHandler(handler, payload));
        }
    }

    /**
     * Adapt a [handler + payload] combination to a {@code Runnable}.
     *
     * @param handler the handler that handles the payload
     * @param payload the payload
     * @return the {@code Runnable} adapter for the handler
     */
    protected abstract Runnable runnableHandler(H handler, P payload);
}
