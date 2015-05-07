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
package com.github.shredder121.gh_event_api.filter;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Throwables;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;

import ch.qos.logback.core.encoder.ByteArrayUtil;

/**
 * A filter that checks the {@link #GITHUB_SIGNATURE_HEADER signature header} to see if the request matches.
 * Specify a {@code secret} property to set up the filter.
 *
 * @author Shredder121
 */
@Component
public class GithubMACChecker extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(GithubMACChecker.class);

    private static final String GITHUB_SIGNATURE_HEADER = "X-Hub-Signature";
    private static final String HMAC_SHA1 = "HmacSHA1";

    private final Key key;

    @Autowired
    public GithubMACChecker(Environment env) {
        String secret = env.getProperty("secret");
        if (secret != null) {
            this.key = new SecretKeySpec(secret.getBytes(UTF_8), HMAC_SHA1);
        } else {
            this.key = null;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String signatureHeader = request.getHeader(GITHUB_SIGNATURE_HEADER);
        if (key != null) {
            if (signatureHeader != null) {
                HttpServletRequest preReadRequest = new PreReadRequest(request);
                byte[] requestBytes = ByteStreams.toByteArray(preReadRequest.getInputStream());
                String signatureString = "sha1=" + hexDigest(requestBytes);
                if (signatureString.hashCode() != signatureHeader.hashCode()) {
                    response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid MAC");
                } else {
                    filterChain.doFilter(preReadRequest, response);
                }
            } else {
                response.sendError(HttpStatus.NOT_FOUND.value());
            }
        } else {
            if (signatureHeader != null) {
                logger.warn("Signature checking requested, but Mac is not set up.");
            }
            filterChain.doFilter(request, response);
        }
    }

    private String hexDigest(byte[] requestBytes) throws IllegalStateException {
        byte[] digest = getMacInstance().doFinal(requestBytes);
        return ByteArrayUtil.toHexString(digest);
    }

    private Mac getMacInstance() {
        try {
            Mac theMac = Mac.getInstance(HMAC_SHA1);
            theMac.init(key);
            return theMac;
        } catch (InvalidKeyException | NoSuchAlgorithmException ex) {
            throw Throwables.propagate(ex);
        }
    }

    private static class PreReadRequest extends HttpServletRequestWrapper {

        private final byte[] input;

        public PreReadRequest(HttpServletRequest request) throws IOException {
            super(request);
            input = ByteStreams.toByteArray(request.getInputStream());
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            final InputStream backingInputStream = ByteSource.wrap(input).openStream();
            return new ServletInputStream() {

                @Override
                public int read() throws IOException {
                    return backingInputStream.read();
                }

                // Servlet 3.1
                @Override
                public boolean isFinished() {
                    throw new UnsupportedOperationException("Not supported.");
                }

                @Override
                public boolean isReady() {
                    throw new UnsupportedOperationException("Not supported.");
                }

                @Override
                public void setReadListener(ReadListener listener) {
                    throw new UnsupportedOperationException("Not supported.");
                }
            };
        }
    }
}
