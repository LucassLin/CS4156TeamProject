package views;

import io.dropwizard.views.View;
import models.InfluencerProfile;

public class InfluencerProfileView extends View {

    private final InfluencerProfile influencerProfile;

    public InfluencerProfileView(InfluencerProfile influencerProfile) {
        super("influencer_profile.ftl");
        this.influencerProfile = influencerProfile;
    }

    public InfluencerProfile getInfluencerProfile() {
        return influencerProfile;
    }
}
