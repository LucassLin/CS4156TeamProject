package views;

import io.dropwizard.views.View;
import models.UserProfile;

public class FollowingView extends View {
    private final UserProfile user;

    public FollowingView(UserProfile user) {
        super("following.ftl");
        this.user = user;
    }

    public UserProfile getUser() { return user; }

}
