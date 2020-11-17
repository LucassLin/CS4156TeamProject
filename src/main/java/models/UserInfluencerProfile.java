package models;

import java.util.ArrayList;
import java.util.Optional;

public class UserInfluencerProfile {

    private UserProfile user;
    private InfluencerProfile influencer;
    String liked;
    private ArrayList<String> comments;
    private Optional<Integer> rating;

    public UserInfluencerProfile(UserProfile user, InfluencerProfile influencer) {
        this.user = user;
        this.influencer = influencer;
        this.liked = "Like";
        this.comments = new ArrayList<>();
        this.rating = null;
    }

    public UserProfile getUser() {
        return user;
    }

    public InfluencerProfile getInfluencer() {
        return influencer;
    }

    public String getLiked() {
        return liked;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public Optional<Integer> getRating() {
        return rating;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public void setRating(Optional<Integer> rating) {
        this.rating = rating;
    }

    public void addComment(String comment){
        this.comments.add(comment);
    }
}
