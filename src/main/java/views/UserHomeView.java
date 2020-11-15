package views;

import io.dropwizard.views.View;
import models.InfluencerProfile;
import models.UserProfile;

import java.util.ArrayList;

public class UserHomeView extends View {

    private final UserProfile userProfile;
    private final ArrayList<InfluencerProfile> influencers;

    public UserHomeView(UserProfile userProfile, ArrayList<InfluencerProfile> influencers){
        super("user_home.ftl");
        this.userProfile = userProfile;
        this.influencers = influencers;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public ArrayList<InfluencerProfile> getInfluencers() {
        return influencers;
    }
}
