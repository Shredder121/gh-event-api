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
package com.github.shredder121.gh_event_api.testutil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.kohsuke.github.extras.OkHttpConnector;

import com.squareup.okhttp.OkUrlFactory;

/**
 * Circumvents GitHub's sub-optimal cache control settings.
 *
 * @author Shredder121
 */
public class RawGitOkHttpConnector extends OkHttpConnector {

    public RawGitOkHttpConnector(OkUrlFactory urlFactory) {
        super(urlFactory);
    }

    @Override
    public HttpURLConnection connect(URL url) throws IOException {
        if (url.getHost().equals("raw.githubusercontent.com")) {
            // GitHub's cache control is quite wonky
            url = URI.create("https://cdn.rawgit.com")
                    .resolve(url.getPath())
                    .toURL();
        }
        return super.connect(url);
    }
}
