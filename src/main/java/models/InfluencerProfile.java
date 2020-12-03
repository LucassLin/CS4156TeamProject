/**
 * This file defines the influencer profile class.
 *
 * @author Xuejing Wang, Chucheng Lin.
 */
package models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "InfluencerProfile")

public class InfluencerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "channelId", nullable = false)
    protected final String channelId;

    @Column(name = "channelName", nullable = false)
    protected final String channelName;

    @Column(name = "type", nullable = false)
    protected final String type;

    @Column(name = "countryCode", nullable = false)
    protected final String countryCode;

    @Column(name = "numOfSubscribers", nullable = false)
    protected final String numOfSubscribers;

    @Column(name = "averagePostViews", nullable = false)
    protected final String averagePostViews;

    @Column(name = "tags", nullable = false)
    protected final String tags;

    @Column(name = "photoLink", nullable = false)
    protected final String photoLink;

    @Column(name = "description", nullable = false)
    protected final String description;

    /**
     * Constructor without channel description.
     *
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
        StringBuffer sb1 = new StringBuffer();
        for (String s : tags) {
            sb1.append(s);
            sb1.append(",");
        }
        this.tags = sb1.toString();
        this.description = "No Description";
    }

    /**
     * Constructor with channel description.
     *
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
        StringBuffer sb1 = new StringBuffer();
        for (String s : tags) {
            sb1.append(s);
            sb1.append(",");
        }
        this.tags = sb1.toString();
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
        String str[] = tags.split(",");
        ArrayList<String> tagsList = new ArrayList<>();
        for (String s: str) {
            tagsList.add(s);
        }
        return tagsList;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getDescription() {
        return description;
    }
}
