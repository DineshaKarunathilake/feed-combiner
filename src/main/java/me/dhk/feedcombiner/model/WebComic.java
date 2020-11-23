package me.dhk.feedcombiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebComic {

    @JsonProperty("num")
    private int number;

    private String year;

    private String month;

    private String day;

    private String title;

    @JsonProperty("img")
    private String imageUrl;

    private String webUrl;

    public WebComic() {
    }

    public WebComic(int number, String year, String month, String day, String title, String imageUrl, String webUrl) {
        this.number = number;
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.imageUrl = imageUrl;
        this.webUrl = webUrl;
    }

    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }


    public String getYear() {
        return year;
    }


    public void setYear(String year) {
        this.year = year;
    }


    public String getMonth() {
        return month;
    }


    public void setMonth(String month) {
        this.month = month;
    }


    public String getDay() {
        return day;
    }


    public void setDay(String day) {
        this.day = day;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getWebUrl() {
        return webUrl;
    }


    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
