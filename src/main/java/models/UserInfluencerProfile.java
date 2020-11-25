/**
 * This file defines the user influencer profile class.
 *
 * @author Xuejing Wang, Chucheng Lin.
 */

package models;

import java.util.ArrayList;
import java.util.Optional;

public class UserInfluencerProfile {

    protected final UserProfile user;
    protected final InfluencerProfile influencer;
    String liked;
    protected final ArrayList<String> comments;
    protected Optional<Integer> rating;

    /**
     * Constructor.
     * @param user
     * UserProfile.
     * @param influencer
     * InfluencerProfile.
     */
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

    public void addComment(String comment) {
        this.comments.add(comment);
    }
}
