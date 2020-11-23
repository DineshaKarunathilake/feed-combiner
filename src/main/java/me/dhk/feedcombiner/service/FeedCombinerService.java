package me.dhk.feedcombiner.service;

import me.dhk.feedcombiner.api.FeedBuilderStrategy;
import me.dhk.feedcombiner.model.ResultFeed;
import me.dhk.feedcombiner.model.ResultFeedComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FeedCombinerService implements IFeedCombinerService {

    @Resource(name = "httpFeedBuilderStrategy")
    private FeedBuilderStrategy httpFeedBuilderStrategy;

    @Resource(name = "rssFeedBuilderStrategy")
    private FeedBuilderStrategy rssFeedBuilderStrategy;

    @Autowired
    public FeedCombinerService(FeedBuilderStrategy httpFeedBuilderStrategy, FeedBuilderStrategy rssFeedBuilderStrategy) {
        this.httpFeedBuilderStrategy = httpFeedBuilderStrategy;
        this.rssFeedBuilderStrategy = rssFeedBuilderStrategy;
    }

    @Override
    public List<ResultFeed> getCombinedFeed() {

        List<ResultFeed> list1 = httpFeedBuilderStrategy.getFeed();

        List<ResultFeed> list2 = rssFeedBuilderStrategy.getFeed();

        return Stream.concat(list1.stream(), list2.stream())
                .sorted(new ResultFeedComparator())
                .collect(Collectors.toList());

    }
}
