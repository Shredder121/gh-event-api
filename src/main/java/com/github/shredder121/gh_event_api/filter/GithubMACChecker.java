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

import static com.github.shredder121.gh_event_api.filter.HeaderNames.GITHUB_EVENT_HEADER;
import static com.github.shredder121.gh_event_api.filter.HeaderNames.GITHUB_SIGNATURE_HEADER;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.function.Supplier;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

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
 * A filter that checks the {@link HeaderNames#GITHUB_SIGNATURE_HEADER signature header} to see if the request matches.
 * Specify a {@code secret} property to set up the filter.
 *
 * @author Shredder121
 */
@Component
class GithubMACChecker extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(GithubMACChecker.class);

    private static final String HMAC_SHA1 = "HmacSHA1";

    private final Supplier<Mac> macProvider;

    @Autowired
    public GithubMACChecker(Environment env) {
        String secret = env.getProperty("secret");
        if (secret != null) {
            Key key = new SecretKeySpec(secret.getBytes(UTF_8), HMAC_SHA1);
            this.macProvider = new MacProvider(key);
        } else {
            this.macProvider = null;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String signatureHeader = request.getHeader(GITHUB_SIGNATURE_HEADER);
        if (shouldFilter(request)) {
            if (signatureHeader != null) {
                HttpServletRequest preReadRequest = new PreReadRequest(request);
                byte[] requestBytes = ByteStreams.toByteArray(preReadRequest.getInputStream());
                String signatureString = "sha1=" + hexDigest(requestBytes);
                if (signatureString.hashCode() != signatureHeader.hashCode()) {
                    logger.warn("bad signature {} {}", signatureString, signatureHeader);
                    response.sendError(HttpStatus.NO_CONTENT.value());
                    // drops the payload
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

    private boolean shouldFilter(HttpServletRequest request) {
        return macProvider != null && request.getHeader(GITHUB_EVENT_HEADER) != null;
    }

    private String hexDigest(byte[] requestBytes) {
        byte[] digest = macProvider.get().doFinal(requestBytes);
        return ByteArrayUtil.toHexString(digest);
    }

    // Extracted since the HmacSHA1 implementation is present, this allows JaCoCo to ignore the catch
    private static final class MacProvider implements Supplier<Mac> {

        private final Key key;

        private MacProvider(Key key) {
            this.key = key;
        }

        @Override
        public Mac get() {
            try {
                Mac theMac = Mac.getInstance(HMAC_SHA1);
                theMac.init(key);
                return theMac;
            } catch (InvalidKeyException | NoSuchAlgorithmException ex) {
                throw Throwables.propagate(ex);
            }
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
            return new PreReadServletInputStream(input);
        }
    }

    private static class PreReadServletInputStream extends ServletInputStream {

        private final InputStream backingInputStream;

        public PreReadServletInputStream(byte[] input) throws IOException {
            this.backingInputStream = ByteSource.wrap(input).openStream();
        }

        @Override
        public int read() throws IOException {
            return backingInputStream.read();
        }

        // Servlet 3.1
        @Override public boolean isFinished() {
            return unsupported();
        }

        @Override public boolean isReady() {
            return unsupported();
        }

        @Override public void setReadListener(ReadListener listener) {
            unsupported();
        }

        private static <T> T unsupported() {
            throw new UnsupportedOperationException("Not supported.");
        }
    }
}
