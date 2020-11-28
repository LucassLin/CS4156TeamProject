package resources;

import com.codahale.metrics.annotation.Timed;
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

    private static int HOME_INFLUENCER_NUM = 12;

    @Context
    private ResourceContext rc;

    @GET
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public void get(final @Context ResourceContext resourceContext) {
        final LikeRecordResource calculatorResource = resourceContext.getResource(LikeRecordResource.class);
    }


    @Path("/login")
    @Timed
    @GET
    public LoginView getWelcome() {
        return new LoginView("Welcome to InfluencerBoard");
    }

    @Path("/home/{name}/{email}")
    @Timed
    @UnitOfWork
    @GET
    public UserHomeView getHomeForUser(@PathParam("name") String name, @PathParam("email") String email,
                                       final @Context ResourceContext resourceContext) throws IOException {
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
        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests, followingChannels);

        return new UserHomeView(user, influencers);
    }

    @Path("/home/{name}/{email}/following")
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

//    @Path("/home/{name}/{email}/{channelId}/{likeInfo}")
//    @GET
//    public UserInfluencerProfileView getInfluencerForUser(@PathParam("name") String name, @PathParam("email") String email, @PathParam("channelId") String channelId, @PathParam("likeInfo") String likeInfo) throws IOException {
//        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/chucheng/Desktop/CS4156/TeamProject/CS4156TeamProject/channelAnalytics.csv");
//        ArrayList<InfluencerProfile> influencers = task.getInfluencers(3);
//        ArrayList<String> interests = new ArrayList<>();
//        interests.add("music");
//        interests.add("movie");
//        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests);
//        InfluencerProfile currInfluencer = null;
//        for (InfluencerProfile i : influencers) {
//            if (i.getChannelId().equals(channelId)){
//                currInfluencer = i;
//            }
//        }
//        UserInfluencerProfile userInfluencerProfile = new UserInfluencerProfile(user, currInfluencer);
//        userInfluencerProfile.setLiked(likeInfo);
////        InfluencerProfile influencer = new InfluencerProfile(
////                influencers.get(0).getChannelId(), "", "", "0000000000", "female",
////                "", tags, influencers.get(0).getPhotoLink());
////        InfluencerProfile influencer = influencers.get(0);
////        System.out.println("This is the link " + influencer.getPhotoLink());
//
//        return new UserInfluencerProfileView(userInfluencerProfile);
//    }

//    @Path("/home/{name}/{email}/{channelId}/{likeInfo}")
//    @POST
//    public void sendUserLikeInfo(@PathParam("likeInfo") boolean likeInfo) {
//        // do something
//    }

    @Path("/home/{name}/{email}/{channelId}")
    @GET
    public InfluencerProfileView getInfluencerForUser(@PathParam("channelId") String channelId)
            throws GeneralSecurityException, IOException {
        Search search = new Search(channelId);
        ArrayList<String> links = search.getVideoList();
        ArrayList<String> threeLinks = new ArrayList<>();
        System.out.println("Total of " + links.size() + " videos are return for channel: " + channelId);
        for (int i = 0; i < 3; ++i) {
            threeLinks.add("https://www.youtube.com/embed/" + links.get(i));
            //System.out.println("link is " + threeLinks.get(i));
        }
        InfluencerProfile curInfluencer = search.getInfluencerProfileByID();
        return new InfluencerProfileView(curInfluencer, threeLinks);
    }

//    @POST
//    @Path("/home/{name}/{email}/{channelId}/likeMe")
//    public void getLikeInfo(@PathParam("channelId") String channelId, @PathParam("email") String email) {
//        LikeRecord record = new LikeRecord(email, channelId);
//        System.out.println(email + " -> " + channelId);
//    }

}