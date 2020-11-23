package me.dhk.feedcombiner.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.dhk.feedcombiner.model.ResultFeed;
import me.dhk.feedcombiner.model.WebComic;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("httpFeedBuilderStrategy")
public class WebcomicFeedBuilderStrategy implements FeedBuilderStrategy {

    private HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();


    @Override
    public List<ResultFeed> getFeed() {
        List<ResultFeed> list = new ArrayList<>();

        for (int index = 0; index < 10; index++) {

            String url = getUrl(index);

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = null;

            try {

                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            } catch (IOException | InterruptedException e) {
                System.err.println(e);
            }

            ResultFeed feed = getResultFeed(url, response.body());

            list.add(feed);
        }

        return list;
    }

    private ResultFeed getResultFeed(String url, String responseBody) {

        ObjectMapper objectMapper = new ObjectMapper();

        WebComic item = null;

        try {
            item = objectMapper.readValue(responseBody, WebComic.class);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }

        if (item != null) {
            item.setWebUrl(url);
        }

        return buildResultFeedFromWebComic(item);
    }

    private String getUrl(int index) {
        String url;
        if (index == 0) {
            url = "http://xkcd.com/info.0.json";
        } else {
            url = "http://xkcd.com/" + index + "/info.0.json";
        }
        return url;
    }


    public ResultFeed buildResultFeedFromWebComic(WebComic item) {

        ResultFeed resultFeed = new ResultFeed();

        resultFeed.setTitle(item.getTitle());
        resultFeed.setWebUrl(item.getWebUrl());
        resultFeed.setPictureUrl(item.getImageUrl());
        resultFeed.setPublishingDate(LocalDate.of(
                Integer.valueOf(item.getYear()),
                Integer.valueOf(item.getMonth()),
                Integer.valueOf(item.getDay())));

        return resultFeed;
    }

}


