/*
 * Copyright 2015 Shredder121.
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
package com.github.shredder121.gh_event_api.controller.create;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.shredder121.gh_event_api.handler.create.CreateEvent;
import com.github.shredder121.gh_event_api.handler.create.CreateHandler;
import com.github.shredder121.gh_event_api.handler.create.CreatePayload;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

@RestController
@RequestMapping(method = POST, headers = "X-GitHub-Event=create")
@ConditionalOnBean(CreateHandler.class)
public class CreateEndpointController {

    private static final Logger logger = LoggerFactory.getLogger(CreateEndpointController.class);

    private final TaskExecutor executor = new TaskExecutorAdapter(ForkJoinPool.commonPool());
    private final Multimap<String, ? extends CreateHandler> handlers;

    @Autowired
    public CreateEndpointController(Collection<? extends CreateHandler> beans) {
        Multimap<String, CreateHandler> createHandlers = LinkedHashMultimap.create();
        for (CreateHandler bean : beans) {
            for (CreateEvent event : bean.getEvents()) {
                createHandlers.put(event.getName(), bean);
            }
        }
        this.handlers = ImmutableSetMultimap.copyOf(createHandlers);
    }

    @RequestMapping
    public void handle(@Valid @RequestBody CreatePayload payload) {
        String refType = payload.getRefType();
        Collection<? extends CreateHandler> refTypeHandlers = handlers.get(refType);
        logger.debug("{} handlers for {}", refTypeHandlers.size(), refType);
        for (CreateHandler handler : refTypeHandlers) {
            executor.execute(runnableHandler(handler, payload));
        }
    }

    private Runnable runnableHandler(CreateHandler handler, CreatePayload createPayload) {
        return () -> handler.handle(createPayload);
    }
}
