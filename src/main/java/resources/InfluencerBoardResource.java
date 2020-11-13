package resources;

import com.codahale.metrics.annotation.Timed;
import models.InfluencerProfile;
import models.UserProfile;
import views.LoginView;
import views.UserHomeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;

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
        ArrayList<InfluencerProfile> influencers = new ArrayList<>();
        ArrayList<String> interests = new ArrayList<>();
        interests.add("music");
        interests.add("movie");
        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests);
        return new UserHomeView(user, influencers);
    }

/*    @Path("/influencer_profile/{influencer_id}")
    @Timed
    @GET
    public InfluencerProfile getInfluencer(@PathParam("influencer_id") String influencerId){
        InfluencerProfile = getInfluencerById(influencerId);
        return InfluencerHomeView(InfluencerProfile);
    }*/

}

