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
package com.github.shredder121.gh_event_api.controller.push;

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

import com.github.shredder121.gh_event_api.handler.push.PushHandler;
import com.github.shredder121.gh_event_api.handler.push.PushPayload;
import com.google.common.collect.Sets;

@RestController
@RequestMapping(method = POST, headers = "X-GitHub-Event=push")
@ConditionalOnBean(PushHandler.class)
public class PushEndpointController {

    private static final Logger logger = LoggerFactory.getLogger(PushEndpointController.class);

    private final TaskExecutor executor = new TaskExecutorAdapter(ForkJoinPool.commonPool());
    private final Collection<PushHandler> handlers = Sets.newLinkedHashSet();

    @Autowired
    public PushEndpointController(Collection<? extends PushHandler> beans) {
        this.handlers.addAll(beans);
    }

    @RequestMapping
    public void handle(@Valid @RequestBody PushPayload payload) {
        logger.debug("{} handlers", handlers.size());
        for (PushHandler handler : handlers) {
            executor.execute(runnableHandler(handler, payload));
        }
    }

    private Runnable runnableHandler(PushHandler handler, PushPayload payload) {
        return () -> handler.handle(payload);
    }
}
