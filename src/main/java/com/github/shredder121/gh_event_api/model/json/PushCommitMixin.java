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
package com.github.shredder121.gh_event_api.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.PushCommit;
import com.google.common.collect.ImmutableList;

/**
 * Mirror object for {@link PushCommit}.
 *
 * @author Shredder121
 */
abstract class PushCommitMixin {

    /**
     * {@link #PushCommitMixin(
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList)}
     *
     * is a mirrored constructor for
     *
     * {@link PushCommit#PushCommit(
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList)}.
     *
     * @param id the id (SHA hash) of the commit
     * @param label the label
     * @param ref the ref of the label
     * @param message the message of the commit
     */
    @JsonCreator
    PushCommitMixin(
            @JsonProperty("id") String id,
            @JsonProperty("label") String label,
            @JsonProperty("ref") String ref,
            @JsonProperty("message") String message,
            @JsonProperty("added") ImmutableList<String> added,
            @JsonProperty("removed") ImmutableList<String> removed,
            @JsonProperty("modified") ImmutableList<String> modified) {
    }
}