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
package com.github.shredder121.gh_event_api.filter;

/**
 * Header names that {@code gh-event-api} processes.
 */
public class HeaderNames {

    public static final String GITHUB_DELIVERY_HEADER = "X-GitHub-Delivery";
    public static final String GITHUB_EVENT_HEADER = "X-GitHub-Event";
    public static final String GITHUB_SIGNATURE_HEADER = "X-Hub-Signature";

    private HeaderNames() {
    }
}