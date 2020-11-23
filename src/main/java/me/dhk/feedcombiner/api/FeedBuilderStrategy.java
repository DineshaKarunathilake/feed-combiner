package me.dhk.feedcombiner.api;

import me.dhk.feedcombiner.model.ResultFeed;

import java.util.List;

public interface FeedBuilderStrategy {

    List<ResultFeed> getFeed();
}
