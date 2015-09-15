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

import java.util.Collection;
import java.util.Collections;

public class Commit {

    private final String sha;
    private final GitCommit commit;
    private final String url;
    private final String html_url;
    private final String comments_url;
    private final User author;
    private final User committer;
    private final Collection<Commit> parents;

    public Commit(String sha, GitCommit commit, String url, String html_url, String comments_url, User author, User committer, Collection<Commit> parents) {
        this.sha = sha;
        this.commit = commit;
        this.url = url;
        this.html_url = html_url;
        this.comments_url = comments_url;
        this.author = author;
        this.committer = committer;
        this.parents = Collections.unmodifiableCollection(parents);
    }

    public String getSha() {
        return sha;
    }

    public GitCommit getCommit() {
        return commit;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public User getAuthor() {
        return author;
    }

    public User getCommitter() {
        return committer;
    }

    public Collection<Commit> getParents() {
        return parents;
    }
}
