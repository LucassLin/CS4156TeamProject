/**
 * This file defines the influencer profile class.
 *
 * @author Xuejing Wang, Chucheng Lin.
 */
package models;

import java.util.ArrayList;

public class InfluencerProfile {

    protected final String channelId;
    protected final String channelName;
    protected final String type;
    protected final String countryCode;
    protected final String numOfSubscribers;
    protected final String averagePostViews;
    protected final ArrayList<String> tags;
    protected final String photoLink;
    protected final String description;

    /**
     * Constructor without channel description.
     * @param channelId
     * the id of the channel
     * @param channelName
     * the name of the channel
     * @param type
     * the type of the channel
     * @param countryCode
     * the country code of the channel
     * @param numOfSubscribers
     * the number of subscribers in this channel
     * @param averagePostViews
     * the average post views of the channel
     * @param tags
     * tags of the channel
     * @param photoLink
     * the photo link of the channel
     */
    public InfluencerProfile(String channelId, String channelName, String type, String countryCode,
                             String numOfSubscribers, String averagePostViews, ArrayList<String> tags,
                             String photoLink) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.type = type;
        this.countryCode = countryCode;
        this.numOfSubscribers = numOfSubscribers;
        this.averagePostViews = averagePostViews;
        this.photoLink = photoLink;
        this.tags = tags;
        this.description = "No Description";
    }

    /**
     * Constructor with channel description.
     * @param channelId
     * the id of the channel
     * @param channelName
     * the name of the channel
     * @param type
     * the type of the channel
     * @param countryCode
     * the country code of the channel
     * @param numOfSubscribers
     * the number of subscribers in this channel
     * @param averagePostViews
     * the average post views of the channel
     * @param tags
     * tags of the channel
     * @param photoLink
     * the photo link of the channel
     * @param description
     * any description
     */
    public InfluencerProfile(String channelId, String channelName, String type, String countryCode,
                             String numOfSubscribers, String averagePostViews, ArrayList<String> tags,
                             String photoLink, String description) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.type = type;
        this.countryCode = countryCode;
        this.numOfSubscribers = numOfSubscribers;
        this.averagePostViews = averagePostViews;
        this.photoLink = photoLink;
        this.tags = tags;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
