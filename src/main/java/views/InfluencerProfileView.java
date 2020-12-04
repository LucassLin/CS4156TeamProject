package views;

import io.dropwizard.views.View;
import models.InfluencerProfile;

import java.util.ArrayList;

public class InfluencerProfileView extends View {

    private final InfluencerProfile influencerProfile;
    private final ArrayList<String> videoLinks;

    /**
     * Constructor.
     *
     * @param influencerProfile
     * the profile of the influencer
     * @param videoLinks
     * links of the video
     */
    public InfluencerProfileView(InfluencerProfile influencerProfile, ArrayList<String> videoLinks) {
        super("influencer_profile.ftl");
        this.influencerProfile = influencerProfile;
        this.videoLinks = videoLinks;
    }

    public InfluencerProfile getInfluencerProfile() {
        return influencerProfile;
    }

    public ArrayList<String> getVideoLinks() {
        return videoLinks;
    }
}
