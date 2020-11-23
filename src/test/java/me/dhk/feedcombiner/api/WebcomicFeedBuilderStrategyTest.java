package me.dhk.feedcombiner.api;

import me.dhk.feedcombiner.model.ResultFeed;
import me.dhk.feedcombiner.model.WebComic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebcomicFeedBuilderStrategyTest {

    @Test
    void shouldMapWebComicToResultingFeed() {

        WebcomicFeedBuilderStrategy webcomicFeedBuilderStrategy = new WebcomicFeedBuilderStrategy();

        int number = 9;
        String title = "Barrel - Part 1";
        String year = "2020";
        String month = "11";
        String day = "19";
        String imageUrl = "https://imgs.xkcd.com/comics/viral_quiz_identity_theft.png";
        String webUrl = "https://xkcd.com/info.0.json";

        WebComic webComic = new WebComic(number, year, month, day, title, imageUrl, webUrl);

        ResultFeed feed = webcomicFeedBuilderStrategy.buildResultFeedFromWebComic(webComic);

        assertEquals(title, feed.getTitle());
        assertEquals(webUrl, feed.getWebUrl());
        assertEquals(imageUrl, feed.getPictureUrl());
        assertEquals(2020, feed.getPublishingDate().getYear());
        assertEquals(11, feed.getPublishingDate().getMonth().getValue());
        assertEquals(19, feed.getPublishingDate().getDayOfMonth());

    }
}
