package views;

import io.dropwizard.views.View;
import models.InfluencerProfile;
import models.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class InfluencerProfileView extends View {

    private final InfluencerProfile influencerProfile;
    private final ArrayList<String> videoLinks;

    public InfluencerProfileView(InfluencerProfile influencerProfile, ArrayList<String> videoLinks){
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
