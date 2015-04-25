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
package com.github.shredder121.gh_event_api.handler.push;

import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Commit;

public class PushPayload {

    @NotNull private final String head;
    @NotNull private final String ref;
    @NotNull private final String before;
    @NotNull private final String after;
    @NotNull private final Collection<Commit> commits;

    @JsonCreator
    public PushPayload(
            @JsonProperty("head") String head,
            @JsonProperty("ref") String ref,
            @JsonProperty("before") String before,
            @JsonProperty("after") String after,
            @JsonProperty("commits") Collection<Commit> commits) {

        this.head = head;
        this.ref = ref;
        this.before = before;
        this.after = after;
        this.commits = Collections.unmodifiableCollection(commits);
    }

    public String getHead() {
        return head;
    }

    public String getRef() {
        return ref;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    public Collection<Commit> getCommits() {
        return commits;
    }
}
