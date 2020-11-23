package me.dhk.feedcombiner.api;

import me.dhk.feedcombiner.model.FeedMessage;
import me.dhk.feedcombiner.model.ResultFeed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RssFeedBuilderStrategyTest {

    @Test
    void getFeed() {
    }

    @Test
    void shouldMapRSSFeedToResultingFeed() {

        RssFeedBuilderStrategy rssFeedBuilderStrategy = new RssFeedBuilderStrategy();


        String title = "What’s Happening";
        String link = "http://feedproxy.google.com/~r/PoorlyDrawnLines/~3/wOvb1cOd-jk/";
        String guid = "http://www.poorlydrawnlines.com/?p=7797";
        String pubDate = "Mon, 19 Oct 2020 18:56:24 +0000";

        FeedMessage message = new FeedMessage(title, link, guid, pubDate);

        ResultFeed resultFeed = rssFeedBuilderStrategy.buildResultFeedFromRssFeed(message);
        assertEquals(title, resultFeed.getTitle());
        assertEquals(link, resultFeed.getWebUrl());
        assertEquals(guid, resultFeed.getPictureUrl());
        assertEquals(2020, resultFeed.getPublishingDate().getYear());
        assertEquals(19, resultFeed.getPublishingDate().getDayOfMonth());

    }
}
