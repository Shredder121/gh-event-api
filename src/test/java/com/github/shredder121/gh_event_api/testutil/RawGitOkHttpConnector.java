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
