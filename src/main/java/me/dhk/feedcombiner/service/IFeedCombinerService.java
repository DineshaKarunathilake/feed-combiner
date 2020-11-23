package me.dhk.feedcombiner.service;

import me.dhk.feedcombiner.model.ResultFeed;

import java.util.List;

public interface IFeedCombinerService {

    List<ResultFeed> getCombinedFeed();
}
