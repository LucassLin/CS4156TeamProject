package views;

import io.dropwizard.views.View;
import models.UserInfluencerProfile;

public class UserInfluencerProfileView extends View {

    private final UserInfluencerProfile userInfluencerProfile;

    /**
     * Constructor.
     *
     * @param userInfluencerProfile
     * the profile of the user as an influencer
     */
    public UserInfluencerProfileView(UserInfluencerProfile userInfluencerProfile) {
        super("user_influencer_profile.ftl");
        this.userInfluencerProfile = userInfluencerProfile;
    }

    public UserInfluencerProfile getUserInfluencerProfile() {
        return userInfluencerProfile;
    }
}
