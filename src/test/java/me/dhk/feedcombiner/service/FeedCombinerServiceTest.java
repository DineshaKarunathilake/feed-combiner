package me.dhk.feedcombiner.service;

import me.dhk.feedcombiner.api.WebcomicFeedBuilderStrategy;
import me.dhk.feedcombiner.api.RssFeedBuilderStrategy;
import me.dhk.feedcombiner.model.ResultFeed;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeedCombinerServiceTest {

    @Test
    void shouldReturnDescendingSortedFeedWithSize20() {
        FeedCombinerService service = new FeedCombinerService(
                new WebcomicFeedBuilderStrategy(),
                new RssFeedBuilderStrategy());

        List<ResultFeed> feed = service.getCombinedFeed();

        assertEquals(20, feed.size());
        assertTrue(feed.get(0).getPublishingDate().isAfter(feed.get(9).getPublishingDate()) ||
                feed.get(0).getPublishingDate().equals(feed.get(9).getPublishingDate()));
        assertTrue(feed.get(10).getPublishingDate().isAfter(feed.get(18).getPublishingDate()) ||
                feed.get(10).getPublishingDate().equals(feed.get(18).getPublishingDate()));

    }

}
