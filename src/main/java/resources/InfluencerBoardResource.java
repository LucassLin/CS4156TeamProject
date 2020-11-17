package resources;

import com.codahale.metrics.annotation.Timed;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import models.InfluencerProfile;
import models.UserProfile;
import tasks.GetChannelAnalyticsTask;
import views.LoginView;
import views.UserHomeView;
import views.InfluencerProfileView;
import tasks.YoutubeAPI;
import tasks.Search;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.security.GeneralSecurityException;
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
    public InfluencerProfileView getInfluencerForUser(@PathParam("channelId") String channelId)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        ArrayList<String> tags = new ArrayList<>();
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/chucheng/Desktop/CS4156/TeamProject/CS4156TeamProject/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(3);
        InfluencerProfile curInfluencer = influencers.get(0);
        for (int i=0; i<3; ++i){
            if(influencers.get(i).getChannelId().equals(channelId)){
                curInfluencer = influencers.get(i);
                System.out.println("Influencer is " + curInfluencer.getChannelId());
                break;
            }
        }
        Search search = new Search(channelId);
        ArrayList<String> links = search.getVideoList();
        ArrayList<String> threeLinks = new ArrayList<>();
        for(int i=0; i<3; ++i){
            threeLinks.add("https://www.youtube.com/embed/" + links.get(i));
            //System.out.println("link is " + threeLinks.get(i));
        }
        return new InfluencerProfileView(curInfluencer, threeLinks);
    }

}

