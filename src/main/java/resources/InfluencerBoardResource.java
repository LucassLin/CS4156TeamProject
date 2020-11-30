package resources;

import com.codahale.metrics.annotation.Timed;
import db.UserProfileDAO;
import io.dropwizard.hibernate.UnitOfWork;
import models.InfluencerProfile;
import models.LikeRecord;
import models.UserProfile;
import tasks.GetChannelAnalyticsTask;
import tasks.Search;
import views.FollowingView;
import views.InfluencerProfileView;
import views.LoginView;
import views.UserHomeView;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Path("")
public class InfluencerBoardResource {

    private static int HOME_INFLUENCER_NUM = 11;
    private final UserProfileDAO userProfileDAO;

    @Context
    private ResourceContext rc;

    public InfluencerBoardResource(UserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }

    @Path("/login")
    @Timed
    @GET
    public LoginView getWelcome() {
        return new LoginView("Welcome to InfluencerBoard");
    }

    @POST
    @UnitOfWork
    public UserProfile createUserProfile(@Valid UserProfile profile) {
        return userProfileDAO.create(profile);
    }

    @Path("/home/{name}/{email}")
    @Timed
    @UnitOfWork
    @GET
    public UserHomeView getHomeForUser(@PathParam("name") String name, @PathParam("email") String email,
                                       final @Context ResourceContext resourceContext) throws IOException {
        // first check if the user exists in our database
        UserProfile user = new UserProfile(name, email);
        List<UserProfile> users = userProfileDAO.getAll();
        boolean existed = false;
        System.out.println("All users in db: ");
        for (UserProfile u : users) {
            System.out.println(u.getName());
            if(user.getName().equals(u.getName()) && user.getEmail().equals(u.getEmail())) {
                existed = true;
            }
        }
        System.out.println("Does the user already exist? " + existed);
        // if new user, add to our database
        if (!existed) {
            userProfileDAO.create(user);
            System.out.println("new user added to db: " + user.getName() + ", " + user.getEmail());
        }
        // get recommendations for the current user
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/xuejing/Desktop/Fall 2020/cloud computing/CS4156TeamProject/src/main/resources/data/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(HOME_INFLUENCER_NUM);
        ArrayList<String> interests = new ArrayList<>();
        ArrayList<InfluencerProfile> followingChannels = new ArrayList<>();
        final LikeRecordResource resource = resourceContext.getResource(LikeRecordResource.class);
        List<LikeRecord> records = resource.listLikes(email);
        for(LikeRecord r : records){
            //System.out.println("record is: " + r.getEmail() + " -> " + r.getChannelID());
            Search search = new Search(r.getChannelID());
            followingChannels.add(search.getInfluencerProfileByID());
        }
        interests.add("music");
        interests.add("movie");
        user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests, followingChannels);

        return new UserHomeView(user, influencers);
    }

    @Path("/home/{name}/{email}/profile")
    @Timed
    @UnitOfWork
    @GET
    public FollowingView getFollowing(@PathParam("name") String name, @PathParam("email") String email,
                                       final @Context ResourceContext resourceContext) throws IOException {
        final LikeRecordResource resource = resourceContext.getResource(LikeRecordResource.class);
        List<LikeRecord> records = resource.listLikes(email);
        ArrayList<InfluencerProfile> followingChannels = new ArrayList<>();
        for(LikeRecord r : records){
            //System.out.println("record is: " + r.getEmail() + " -> " + r.getChannelID());
            Search search = new Search(r.getChannelID());
            followingChannels.add(search.getInfluencerProfileByID());
        }
        ArrayList<String> interests = new ArrayList<>();
        interests.add("music");
        interests.add("movie");
        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests, followingChannels);
        return new FollowingView(user);
    }


    @Path("/home/{name}/{email}/{channelId}")
    @GET
    public InfluencerProfileView getInfluencerForUser(@PathParam("channelId") String channelId)
            throws GeneralSecurityException, IOException {
        Search search = new Search(channelId);
        ArrayList<String> links = search.getVideoList();
        ArrayList<String> threeLinks = new ArrayList<>();
        System.out.println("Total of " + links.size() + " videos are return for channel: " + channelId);
        if (links.size() >= 3) {
            for (int i = 0; i < 3; ++i) {
                threeLinks.add("https://www.youtube.com/embed/" + links.get(i));
                //System.out.println("link is " + threeLinks.get(i));
            }
        }
        InfluencerProfile curInfluencer = search.getInfluencerProfileByID();
        return new InfluencerProfileView(curInfluencer, threeLinks);
    }

}