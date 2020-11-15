package resources;

import com.codahale.metrics.annotation.Timed;
import models.InfluencerProfile;
import models.UserProfile;
import views.LoginView;
import views.UserHomeView;
import views.InfluencerProfileView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @Path("/home/{userId}/{name}/{email}")
    @Produces("text/html;charset=UTF-8")
    @GET
    public UserHomeView getHomeForUser(@PathParam("userId") String userId, @PathParam("name") String name, @PathParam("email") String email){
        ArrayList<InfluencerProfile> influencers = new ArrayList<>();
        ArrayList<String> interests = new ArrayList<>();
        interests.add("music");
        interests.add("movie");
        UserProfile user = new UserProfile(userId, name, email, "0000000000", "female", 19, "China", interests);
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

