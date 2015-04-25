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
package com.github.shredder121.gh_event_api.model;

import java.time.ZonedDateTime;
import java.util.Map;

public class PullRequest {

    private final String url;
    private final Integer number;
    private final String state;
    private final Boolean locked;
    private final String title;
    private final ZonedDateTime created_at;
    private final Commit head;
    private final Map<String, Link> _links;

    public PullRequest(String url, Integer number, String state, Boolean locked, String title, ZonedDateTime created_at, Commit head, Map<String, Link> _links) {
        this.url = url;
        this.number = number;
        this.state = state;
        this.locked = locked;
        this.title = title;
        this.created_at = created_at;
        this.head = head;
        this._links = _links;
    }

    public String getUrl() {
        return url;
    }

    public Integer getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public Boolean getLocked() {
        return locked;
    }

    public String getTitle() {
        return title;
    }

    public ZonedDateTime getCreated_at() {
        return created_at;
    }

    public Commit getHead() {
        return head;
    }

    public Map<String, Link> getLinks() {
        return _links;
    }
}
