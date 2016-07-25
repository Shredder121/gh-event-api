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

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;
import com.google.common.collect.ImmutableList;

/**
 * A commit object that is included in PushPayloads.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
@lombok.AllArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class PushCommit {

    /**
     * Commit id of the commit.
     */
    String id;

    /**
     * The Git tree object this commit is attached to.
     */
    String treeId;

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
}
