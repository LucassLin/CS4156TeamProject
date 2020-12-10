package views;

import io.dropwizard.views.View;
import models.CommentRecord;
import models.InfluencerProfile;

import java.util.ArrayList;
import java.util.List;

public class InfluencerProfileView extends View {

    private final InfluencerProfile influencerProfile;
    private final ArrayList<String> videoLinks;
    private final List<CommentRecord> comments;
    private final String email;
    private final String name;

    /**
     * Constructor.
     *
     * @param influencerProfile
     * the profile of the influencer
     * @param videoLinks
     * links of the video
     */
    public InfluencerProfileView(String name, String email, InfluencerProfile influencerProfile, ArrayList<String> videoLinks, List<CommentRecord> comments) {
        super("influencer_profile.ftl");
        this.influencerProfile = influencerProfile;
        this.videoLinks = videoLinks;
        this.comments = comments;
        this.email = email;
        this.name = name;
    }

    public InfluencerProfile getInfluencerProfile() {
        return influencerProfile;
    }

    public ArrayList<String> getVideoLinks() {
        return videoLinks;
    }

    public List<CommentRecord> getComments() { return comments; }

    public String getEmail() {
        return email;
    }

    public String getName() { return name; }
}
