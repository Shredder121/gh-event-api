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
package com.github.shredder121.gh_event_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

/**
 * A commit object that is included in PushPayloads.
 *
 * @author Shredder121
 */
@lombok.Value
public class PushCommit {

    /**
     * Commit id of the commit.
     */
    String id;

    /**
     * Label of the commit.
     */
    String label;

    /**
     * Ref
     */
    String ref;

    /**
     * Message
     */
    String message;

    /**
     * Files that were added
     */
    ImmutableList<String> added;

    /**
     * Files that were removed
     */
    ImmutableList<String> removed;

    /**
     * Files that were modified
     */
    ImmutableList<String> modified;

    @JsonCreator
    PushCommit(
            @JsonProperty("id") String id,
            @JsonProperty("label") String label,
            @JsonProperty("ref") String ref,
            @JsonProperty("message") String message,
            @JsonProperty("added") ImmutableList<String> added,
            @JsonProperty("removed") ImmutableList<String> removed,
            @JsonProperty("modified") ImmutableList<String> modified) {

        this.id = id;
        this.label = label;
        this.ref = ref;
        this.message = message;
        this.added = added;
        this.removed = removed;
        this.modified = modified;
    }
}
