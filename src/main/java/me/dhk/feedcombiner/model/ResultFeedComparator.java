package me.dhk.feedcombiner.model;

import java.util.Comparator;

public class ResultFeedComparator implements Comparator<ResultFeed> {

    @Override
    public int compare(ResultFeed feed1, ResultFeed feed2) {
        return feed2.getPublishingDate().compareTo(feed1.getPublishingDate());
    }
}
