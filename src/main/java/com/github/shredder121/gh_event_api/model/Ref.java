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

public class Ref {

    private final String label;
    private final String ref;
    private final String sha;
    private final User user;
    private final Repository repo;

    public Ref(String label, String ref, String sha, User user, Repository repo) {
        this.label = label;
        this.ref = ref;
        this.sha = sha;
        this.user = user;
        this.repo = repo;
    }

    public String getLabel() {
        return label;
    }

    public String getRef() {
        return ref;
    }

    public String getSha() {
        return sha;
    }

    public User getUser() {
        return user;
    }

    public Repository getRepo() {
        return repo;
    }
}
