package views;

import io.dropwizard.views.View;
import models.UserInfluencerProfile;
import models.UserProfile;
import org.mortbay.jetty.security.HTAccessHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class FollowingView extends View {
    private final UserProfile user;

    public FollowingView(UserProfile user) {
        super("following.ftl");
        this.user = user;
    }

    public UserProfile getUser() { return user; }

}
