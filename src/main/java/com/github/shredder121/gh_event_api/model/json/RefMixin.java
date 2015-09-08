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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shredder121.gh_event_api.model.Ref;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

/**
 * Mirror object for {@link Ref}.
 *
 * @author Shredder121
 */
class RefMixin {

    /**
     * {@link #RefMixin(java.lang.String, java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.User, com.github.shredder121.gh_event_api.model.Repository)}
     * is a mirrored constructor for
     * {@link Ref#Ref(java.lang.String, java.lang.String, java.lang.String, com.github.shredder121.gh_event_api.model.User, com.github.shredder121.gh_event_api.model.Repository)}
     *
     * @param label the label to inject
     * @param ref the ref to inject
     * @param sha the sha hash to inject
     * @param user the user to inject
     * @param repo the repo to inject
     */
    public RefMixin(
            @JsonProperty("label") String label,
            @JsonProperty("ref") String ref,
            @JsonProperty("sha") String sha,
            @JsonProperty("user") User user,
            @JsonProperty("repo") Repository repo) {
    }

}
