/*
 * Copyright 2017 Shredder121.
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
package com.github.shredder121.gh_event_api.handler.issues;

/**
 * The handler interface for receiving {@code issues} events.
 *
 * @author Shredder121
 * @see <a href="https://developer.github.com/v3/activity/events/types/#issuesevent">The issues event on GitHub</a>
 */
@FunctionalInterface
public interface IssuesHandler {

    void handle(IssuesPayload payload);
}
