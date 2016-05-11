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
package com.github.shredder121.gh_event_api.handler.ping;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * In order to confirm correct configuration gh-event-api
 * echoes GitHub's {@code ping} event back.
 *
 * @author Shredder121
 */
@RestController
@RequestMapping(headers = "X-GitHub-Event=ping")
class PingEndpointController {

    static Logger logger = LoggerFactory.getLogger(PingEndpointController.class);

    /**
     * Handle the {@link PingPayload} by echoing the {@link PingPayload#getZen()}.
     *
     * @param payload the incoming payload
     * @return the zen
     */
    @PostMapping
    public String ping(@Valid @RequestBody PingPayload payload) {
        logger.info("received Ping event - {}", payload);
        return payload.getZen();
    }
}
