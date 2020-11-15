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
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/xuejing/Desktop/Fall 2020/software engineer/CS4156TeamProject/src/main/resources/data/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(3);
        ArrayList<String> interests = new ArrayList<>();
        interests.add("music");
        interests.add("movie");
        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests);
        return new UserHomeView(user, influencers);
    }

    @Path("/home/{userId}/{name}/{email}/{channelId}")
    @Produces("text/html;charset=UTF-8")
    @GET
    public InfluencerProfileView getInfluencerForUser(@PathParam("channelId") Integer channelId){
        Map<String, Integer> cntMap = new HashMap<String, Integer>();
        InfluencerProfile influencer = new InfluencerProfile(
                channelId, "", "", "0000000000", "female",
                19, 1111, cntMap, "", "", 10);
        return new InfluencerProfileView(influencer);
    }

}

