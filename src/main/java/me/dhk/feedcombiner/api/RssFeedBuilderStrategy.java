package me.dhk.feedcombiner.api;

import me.dhk.feedcombiner.model.FeedMessage;
import me.dhk.feedcombiner.model.ResultFeed;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component("rssFeedBuilderStrategy")
public class RssFeedBuilderStrategy implements FeedBuilderStrategy {

    static final String ITEM = "item";
    static final String TITLE = "title";
    static final String LINK = "link";
    static final String GUID = "guid";
    static final String PUB_DATE = "pubDate";

    static final String URL_STRING = "http://feeds.feedburner.com/PoorlyDrawnLines";

    private URL url;

    public RssFeedBuilderStrategy() {
        try {
            this.url = new URL(URL_STRING);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<ResultFeed> getFeed() {

        List<ResultFeed> list = new ArrayList<>();

        try {
            boolean isFeedHeader = true;


            String title = "";
            String link = "";
            String guid = "";
            String pubDate = "";

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();

            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubDate = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {

                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedMessage feedMessage = new FeedMessage();
                        feedMessage.setTitle(title);
                        feedMessage.setLink(link);
                        feedMessage.setGuid(guid);
                        feedMessage.setPubDate(pubDate);
                        list.add(buildResultFeedFromRssFeed(feedMessage));
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }

        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ResultFeed buildResultFeedFromRssFeed(FeedMessage item) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy kk:mm:ss Z");

        ResultFeed resultFeed = new ResultFeed();

        resultFeed.setTitle(item.getTitle());
        resultFeed.setWebUrl(item.getLink());
        resultFeed.setPictureUrl(item.getGuid());
        resultFeed.setPublishingDate(LocalDate.parse(item.getPubDate(), formatter));

        return resultFeed;

    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }

        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
