package me.dhk.feedcombiner.api.controller;

import me.dhk.feedcombiner.model.ResultFeed;
import me.dhk.feedcombiner.service.IFeedCombinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class FeedController {

    private IFeedCombinerService service;

    @Autowired
    public FeedController(IFeedCombinerService service) {
        this.service = service;
    }

    @GetMapping
    List<ResultFeed> findFeed() {
        return service.getCombinedFeed();
    }


}
