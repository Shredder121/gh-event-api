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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.github.shredder121.gh_event_api.model.*;

/**
 * The Jackson Module that links the model objects with its
 * {@link com.github.shredder121.gh_event_api.model.json mirror object}.
 *
 * @author Shredder121
 */
@Component
@SuppressWarnings("serial") // Interoperability is not required
class GHEventApiModule extends SimpleModule {

    public GHEventApiModule() {
        super("GHEventApiServer");

        setMixInAnnotation(CommitComment.class, CommitCommentMixin.class);
        setMixInAnnotation(Label.class, LabelMixin.class);
        setMixInAnnotation(Link.class, LinkMixin.class);
        setMixInAnnotation(PullRequest.class, PullRequestMixin.class);
        setMixInAnnotation(PushCommit.class, PushCommitMixin.class);
        setMixInAnnotation(Ref.class, RefMixin.class);
        setMixInAnnotation(Repository.class, RepositoryMixin.class);
        setMixInAnnotation(StatusBranch.class, StatusBranchMixin.class);
        setMixInAnnotation(StatusCommit.class, StatusCommitMixin.class);
        setMixInAnnotation(User.class, UserMixin.class);
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        // https://github.com/spring-projects/spring-boot/issues/4490
        new GuavaModule().setupModule(context);
    }
}
