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
package com.github.shredder121.gh_event_api.filter;

import java.io.Closeable;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.google.common.io.Closer;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

/**
 * A Filter implementation based on {@link MDCInsertingServletFilter} that takes GitHub specific headers into account.
 *
 * @author Shredder121
 */
@Component
class GithubMDCInsertingServletFilter extends MDCInsertingServletFilter {

    private static final String GITHUB_DELIVERY_HEADER = "X-GitHub-Delivery";
    private static final String GITHUB_EVENT_HEADER = "X-GitHub-Event";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            try (Closeable customHeaders = customHeaders(Closer.create(), (HttpServletRequest) req)) {
                super.doFilter(req, res, chain);
            }
        } else {
            super.doFilter(req, res, chain);
        }
    }

    private Closeable customHeaders(Closer closer, HttpServletRequest request) {
        closer.register(closeableHeader(request, GITHUB_DELIVERY_HEADER));
        closer.register(closeableHeader(request, GITHUB_EVENT_HEADER));
        return closer;
    }

    private static Closeable closeableHeader(HttpServletRequest request, String headerName) {
        return MDC.putCloseable(headerName, request.getHeader(headerName));
    }
}
