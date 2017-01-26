package com.github.shredder121.gh_event_api.model;

import static com.github.shredder121.gh_event_api.testutil.HamcrestHelpers.*;
import static org.hamcrest.Matchers.*;

public class WikiPageSerializationTest extends AbstractSerializationTest<WikiPage> {

    static String SAMPLE_WIKI_PAGE
            = "{\n"
            + "  \"page_name\": \"Home\",\n"
            + "  \"title\": \"Home\",\n"
            + "  \"summary\": null,\n"
            + "  \"action\": \"created\",\n"
            + "  \"sha\": \"dfc70625d16d47316ee20283240740f76cf3d195\",\n"
            + "  \"html_url\": \"https://github.com/Shredder121-me/yummy-octo-turtle/wiki/Home\"\n"
            + "}";

    public WikiPageSerializationTest() {
        super(WikiPage.class);
    }

    @Override
    protected String getSample() {
        return SAMPLE_WIKI_PAGE;
    }

    @Override
    protected void checkDeserialized(WikiPage wikiPage) {
        errorCollector.checkThat(wikiPage, allOf(
                property(WikiPage::getPageName, is("Home")),
                property(WikiPage::getTitle, is("Home")),
                property(WikiPage::getSummary, is(nullValue())),
                property(WikiPage::getAction, is("created")),
                property(WikiPage::getSha, is("dfc70625d16d47316ee20283240740f76cf3d195")),
                property(WikiPage::getHtmlUrl, is("https://github.com/Shredder121-me/yummy-octo-turtle/wiki/Home"))
        ));
    }
}
