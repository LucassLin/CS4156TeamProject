package views;

import io.dropwizard.views.View;

import java.util.ArrayList;

public class FollowingView extends View {
    private final ArrayList<String> following;

    public FollowingView(ArrayList<String> following) {
        super("following.ftl");
        this.following = following;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }
}
