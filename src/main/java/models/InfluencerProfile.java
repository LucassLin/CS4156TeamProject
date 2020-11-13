package models;

import java.util.Map;

public class InfluencerProfile {

    private int channelId;
    private String type;
    private String countryCode;
    private String ageGroup;
    private String gender;
    private int numOfSubscribers;
    private int averagePostViews;
    private Map<String, Integer> tagFreq;
    private String channelLink;
    private String photoLink;
    private double rating;

    public InfluencerProfile(int channelId, String type, String countryCode, String ageGroup, String gender,
                             int numOfSubscribers, int averagePostViews, Map<String, Integer> tagFreq, String channelLink,
                             String photoLink, double rating) {
        this.channelId = channelId;
        this.type = type;
        this.countryCode = countryCode;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.numOfSubscribers = numOfSubscribers;
        this.averagePostViews = averagePostViews;
        this.tagFreq = tagFreq;
        this.channelLink = channelLink;
        this.photoLink = photoLink;
        this.rating = rating;
    }

    public int getChannelId() {
        return channelId;
    }

    public String getType() {
        return type;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getGender() {
        return gender;
    }

    public int getNumOfSubscribers() {
        return numOfSubscribers;
    }

    public int getAveragePostViews() {
        return averagePostViews;
    }

    public Map<String, Integer> getTagFreq() {
        return tagFreq;
    }

    public String getChannelLink() {
        return channelLink;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public double getRating() {
        return rating;
    }
}
