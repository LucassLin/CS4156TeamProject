package views;

import io.dropwizard.views.View;
import models.InfluencerProfile;
import models.UserProfile;

import java.util.ArrayList;

public class RecommendationView extends View {
    private final UserProfile userProfile;
    private final ArrayList<InfluencerProfile> influencers;
    private ArrayList<String> followingChannelIds = new ArrayList<>();

    /**
     * Constructor.
     * @param influencers
     * the list of influencers
     */
    public RecommendationView(ArrayList<InfluencerProfile> influencers, UserProfile userProfile,
                              ArrayList<String> followingChannelIds) {
        super("recommendation.ftl");
        this.influencers = influencers;
        this.userProfile = userProfile;
        this.followingChannelIds = followingChannelIds;
    }

    public ArrayList<InfluencerProfile> getInfluencers() {
        return influencers;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public ArrayList<String> getFollowingChannelIds() { return followingChannelIds;}
}
