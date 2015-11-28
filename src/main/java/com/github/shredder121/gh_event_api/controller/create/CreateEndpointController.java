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

import static java.util.Collections.emptyList;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.shredder121.gh_event_api.handler.create.CreateEvent;
import com.github.shredder121.gh_event_api.handler.create.CreateHandler;
import com.github.shredder121.gh_event_api.handler.create.CreatePayload;

@RestController
@RequestMapping(method = POST, headers = "X-GitHub-Event=create")
@ConditionalOnBean(CreateHandler.class)
public class CreateEndpointController {

    private static final Logger logger = LoggerFactory.getLogger(CreateEndpointController.class);

    private final TaskExecutor executor = new TaskExecutorAdapter(ForkJoinPool.commonPool());
    private final MultiValueMap<String, CreateHandler> handlers = new LinkedMultiValueMap<>();

    @Autowired
    public CreateEndpointController(Collection<? extends CreateHandler> beans) {
        for (CreateHandler bean : beans) {
            for (CreateEvent event : bean.getEvents()) {
                handlers.add(event.getName(), bean);
            }
        }
    }

    @RequestMapping
    public void handle(@Valid @RequestBody CreatePayload createPayload) {
        String refType = createPayload.getRefType();
        List<CreateHandler> refTypeHandlers = handlers.getOrDefault(refType, emptyList());
        logger.debug("{} handlers for {}", refTypeHandlers.size(), refType);
        refTypeHandlers.stream()
                .map(handler -> runnableHandler(handler, createPayload))
                .forEach(executor::execute);
    }

    private Runnable runnableHandler(CreateHandler handler, CreatePayload createPayload) {
        return () -> handler.handle(createPayload);
    }
}
