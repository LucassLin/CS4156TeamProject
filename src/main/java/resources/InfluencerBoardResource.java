package resources;

import com.codahale.metrics.annotation.Timed;
import models.InfluencerProfile;
import models.UserProfile;
import tasks.GetChannelAnalyticsTask;
import views.LoginView;
import views.UserHomeView;
import views.InfluencerProfileView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Path("")
public class InfluencerBoardResource {

    @Path("/login")
    @Timed
    @GET
    public LoginView getWelcome(){
        return new LoginView("Welcome to InfluencerBoard");
    }

    @Path("/home/{name}/{email}")
    @Timed
    @GET
    public UserHomeView getHomeForUser(@PathParam("name") String name, @PathParam("email") String email){
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/chucheng/Desktop/CS4156/TeamProject/CS4156TeamProject/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(3);
        ArrayList<String> interests = new ArrayList<>();
        interests.add("music");
        interests.add("movie");
        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests);
        return new UserHomeView(user, influencers);
    }

    @Path("/home/{name}/{email}/{channelId}")
    @GET
    public InfluencerProfileView getInfluencerForUser(@PathParam("channelId") String channelId){
        ArrayList<String> tags = new ArrayList<>();
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/chucheng/Desktop/CS4156/TeamProject/CS4156TeamProject/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(3);

//        InfluencerProfile influencer = new InfluencerProfile(
//                influencers.get(0).getChannelId(), "", "", "0000000000", "female",
//                "", tags, influencers.get(0).getPhotoLink());
//        InfluencerProfile influencer = influencers.get(0);
//        System.out.println("This is the link " + influencer.getPhotoLink());

        return new InfluencerProfileView(influencers.get(0));
    }

}

