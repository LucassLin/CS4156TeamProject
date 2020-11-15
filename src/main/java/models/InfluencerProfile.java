package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class InfluencerProfile {

    private String channelId;
    private String channelName;
    private String type;
    private String countryCode;
    private String numOfSubscribers;
    private String averagePostViews;
    private ArrayList<String> tags;
    //private Map<String, Integer> tagFreq;
    //private String channelLink;
    private String photoLink;
    //private double rating;

    public InfluencerProfile(String channelId, String channelName, String type, String countryCode,
                             String numOfSubscribers, String averagePostViews, ArrayList<String> tags,
                             String photoLink) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.type = type;
        this.countryCode = countryCode;
        this.numOfSubscribers = numOfSubscribers;
        this.averagePostViews = averagePostViews;
        //this.tagFreq = tagFreq;
        //this.channelLink = channelLink;
        this.photoLink = photoLink;
        //this.rating = rating;
        this.tags = tags;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getType() {
        return type;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumOfSubscribers() {
        return numOfSubscribers;
    }

    public String getAveragePostViews() {
        return averagePostViews;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getChannelName() {
        return channelName;
    }
}
