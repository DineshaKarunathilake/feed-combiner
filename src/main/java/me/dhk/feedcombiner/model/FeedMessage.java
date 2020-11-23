package me.dhk.feedcombiner.model;

public class FeedMessage {

    private String title;
    private String link;
    private String guid;
    private String pubDate;


    public FeedMessage() {
    }


    public FeedMessage(String title, String link, String guid, String pubDate) {
        this.title = title;
        this.link = link;
        this.guid = guid;
        this.pubDate = pubDate;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }


    public String getGuid() {
        return guid;
    }


    public void setGuid(String guid) {
        this.guid = guid;
    }


    public String getPubDate() {
        return pubDate;
    }


    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
