package views;

import io.dropwizard.views.View;
import models.UserInfluencerProfile;
import models.UserProfile;

import java.util.ArrayList;

public class FollowingView extends View {
    private final ArrayList<String> following;
    private final UserProfile user;

    public FollowingView(ArrayList<String> following, UserProfile user) {
        super("following.ftl");
        this.following = following;
        this.user = user;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public UserProfile getUser() { return user; }
}
