package resources;

import com.codahale.metrics.annotation.Timed;
import models.InfluencerProfile;
import models.UserInfluencerProfile;
import models.UserProfile;
import tasks.GetChannelAnalyticsTask;
import views.UserInfluencerProfileView;
import views.LoginView;
import views.UserHomeView;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.ArrayList;

@Path("")
public class InfluencerBoardResource {

    @Path("/login")
    @Timed
    @GET
    public LoginView getWelcome() {
        return new LoginView("Welcome to InfluencerBoard");
    }

    @Path("/home/{name}/{email}")
    @Timed
    @GET
    public UserHomeView getHomeForUser(@PathParam("name") String name, @PathParam("email") String email) throws IOException {
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/xuejing/Desktop/Fall 2020/software engineer/CS4156TeamProject/src/main/resources/data/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(3);
        ArrayList<String> interests = new ArrayList<>();
        interests.add("music");
        interests.add("movie");
        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests);
        return new UserHomeView(user, influencers);
    }

    @Path("/home/{name}/{email}/{channelId}/{likeInfo}")
    @GET
    public UserInfluencerProfileView getInfluencerForUser(@PathParam("name") String name, @PathParam("email") String email, @PathParam("channelId") String channelId, @PathParam("likeInfo") String likeInfo) throws IOException {
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/xuejing/Desktop/Fall 2020/software engineer/CS4156TeamProject/src/main/resources/data/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(3);
        ArrayList<String> interests = new ArrayList<>();
        interests.add("music");
        interests.add("movie");
        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests);
        InfluencerProfile currInfluencer = null;
        for (InfluencerProfile i : influencers) {
            if (i.getChannelId().equals(channelId)){
                currInfluencer = i;
            }
        }
        UserInfluencerProfile userInfluencerProfile = new UserInfluencerProfile(user, currInfluencer);
        userInfluencerProfile.setLiked(likeInfo);
//        InfluencerProfile influencer = new InfluencerProfile(
//                influencers.get(0).getChannelId(), "", "", "0000000000", "female",
//                "", tags, influencers.get(0).getPhotoLink());
//        InfluencerProfile influencer = influencers.get(0);
//        System.out.println("This is the link " + influencer.getPhotoLink());

        return new UserInfluencerProfileView(userInfluencerProfile);
    }

    @Path("/home/{name}/{email}/{channelId}/{likeInfo}")
    @POST
    public void sendUserLikeInfo(@PathParam("likeInfo") boolean likeInfo) {
        // do something
    }

}

