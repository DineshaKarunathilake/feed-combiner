package me.dhk.feedcombiner.model;

import java.time.LocalDate;

public class ResultFeed {

    private String title;
    private String pictureUrl;
    private String webUrl;
    private LocalDate publishingDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPictureUrl() {
        return pictureUrl;
    }


    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public String getWebUrl() {
        return webUrl;
    }


    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }


    public LocalDate getPublishingDate() {
        return publishingDate;
    }


    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }
}
