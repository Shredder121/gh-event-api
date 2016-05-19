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

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.shredder121.gh_event_api.model.json.PropertyBasedJsonCreator;

/**
 * A branch object that is included in StatusPayloads.
 *
 * @author Shredder121
 */
@lombok.Value
@JsonNaming(SnakeCaseStrategy.class)
@lombok.RequiredArgsConstructor(
        access = lombok.AccessLevel.MODULE,
        onConstructor = @__(@PropertyBasedJsonCreator)
)
public class StatusBranch {

    /**
     * The name of the branch.
     */
    String name;

    /**
     * The head commit of the branch.
     */
    StatusCommit commit;
}
