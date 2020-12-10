package resources;

import com.codahale.metrics.annotation.Timed;
import db.LikeRecordDAO;
import db.UserProfileDAO;
import io.dropwizard.hibernate.UnitOfWork;
import models.InfluencerProfile;
import models.LikeRecord;
import models.UserProfile;
import tasks.GetChannelAnalyticsTask;
import tasks.Search;
import views.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("")
public class InfluencerBoardResource {

    private static int HOME_INFLUENCER_NUM = 11;
    @Inject
    private final UserProfileDAO userProfileDAO;
/*    @Inject
    private final InfluencerProfileDAO influencerProfileDAO;*/
    @Inject
    private final LikeRecordDAO likeRecordDAO;

    public InfluencerBoardResource(UserProfileDAO userProfileDAO, LikeRecordDAO likeRecordDAO) {
        this.userProfileDAO = userProfileDAO;
        this.likeRecordDAO = likeRecordDAO;
    }

    public List<LikeRecord> findLikeRecordByEmail(String email) {
        return likeRecordDAO.findAll(email);
    }

    @POST
    @UnitOfWork
    @Path("/LikeRecord/addRecord/{email}/{channelId}/{tagsString}")
    public void addLikeRecord(@PathParam("channelId") String channelId, @PathParam("email") String email
    , @PathParam("tagsString") String newTags) {
        System.out.println("new tags are " + newTags);
        if (channelId == null || email == null) {
            return;
        }
        LikeRecord record = new LikeRecord(email, channelId);
        List<LikeRecord> allLikes = likeRecordDAO.findAll(email);
        boolean duplicate = false;
        for (LikeRecord records : allLikes) {
            if (records.getChannelID().equals(channelId) && records.getEmail().equals(email)) {
                duplicate = true;
                break;
            }
        }
        if (!duplicate) {
            likeRecordDAO.create(record);
            System.out.println("new like recorded added");
            UserProfile profile = userProfileDAO.getUserProfile(email).get(0);
            String newInterests = newTags;
            for (String s: profile.getInterests()) {
                newInterests += s + ",";
            }
            System.out.println("newInterests is " + newInterests);
            int updateStatus = userProfileDAO.updateUserProfile(email, newInterests);
            System.out.println("update Status is " + updateStatus);
            List<UserProfile> userProfileList = userProfileDAO.getUserProfile(email);
            for(String s : userProfileList.get(0).getInterests()){
                System.out.println("interest is " +s);
            }
        }
    }

    @POST
    @UnitOfWork
    @Path("/LikeRecord/deleteRecord/{email}/{channelId}")
    public void deleteLikeRecord(@PathParam("channelId") String channelId, @PathParam("email") String email) {
        if (channelId == null || email == null) {
            return;
        }
        System.out.println("record to be deleted: " + email + " -> " + channelId);
        int deleteStatus = likeRecordDAO.deleteRecord(email, channelId);
        System.out.println("#entry deleted: " + deleteStatus);
        List<LikeRecord> allLikes = likeRecordDAO.findAll(email);
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
    public UserHomeView getHomeForUser(@PathParam("name") String name, @PathParam("email") String email) throws IOException {
        if (name == null || email == null) {
            return null;
        }
        // first check if the user exists in our database
        UserProfile user = new UserProfile(name, email);
        List<UserProfile> users = userProfileDAO.getAllUsers();
        boolean existed = false;
        System.out.println("All users in db: ");
        for (UserProfile u : users) {
            System.out.println(u.getName());
            if (user.getName().equals(u.getName()) && user.getEmail().equals(u.getEmail())) {
                existed = true;
            }
        }
        System.out.println("Does the user already exist? " + existed);
        // if new user, add to our database
//        if (!existed) {
//            userProfileDAO.createUser(user);
//            System.out.println("new user added to db: " + user.getName() + ", " + user.getEmail());
//        }
        // get recommendations for the current user
        String projDir = System.getProperty("user.dir");
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask(projDir+"/channelAnalytics.csv");
        ArrayList<InfluencerProfile> pool = task.getInfluencers(300);
        Collections.shuffle(pool);
        ArrayList<InfluencerProfile> influencers = new ArrayList<>();
        for (int i = 0; i < HOME_INFLUENCER_NUM; ++i) {
            influencers.add(pool.get(i));
        }
        ArrayList<String> interests = new ArrayList<>();
        ArrayList<InfluencerProfile> followingChannels = new ArrayList<>();
        List<LikeRecord> records = likeRecordDAO.findAll(email);
        System.out.println("all like records:");
        System.out.println(records.size());
        if (records.size() != 0) {
            for (LikeRecord r : records) {
                System.out.println("record is: " + r.getEmail() + " -> " + r.getChannelID());
                Search search = new Search(r.getChannelID());
                followingChannels.add(search.getInfluencerProfileByID());
            }
        }
        interests.add("music");
        interests.add("movie");
        interests.add("nba");
        interests.add("cba");
        user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests, followingChannels);
        if (!existed) {
            userProfileDAO.createUser(user);
            System.out.println("new user added to db: " + user.getName() + ", " + user.getEmail());
        }
        return new UserHomeView(user, influencers);
    }

    @Path("/home/{name}/{email}/profile")
    @Timed
    @UnitOfWork
    @GET
    public FollowingView getFollowing(@PathParam("name") String name, @PathParam("email") String email) throws IOException {
        if (name == null || email == null) {
            return null;
        }
        List<LikeRecord> records = likeRecordDAO.findAll(email);
        System.out.println("number of records for following page: " + records.size());
        ArrayList<InfluencerProfile> followingChannels = new ArrayList<>();
        for (LikeRecord r : records) {
            System.out.println("record is: " + r.getEmail() + " -> " + r.getChannelID());
            Search search = new Search(r.getChannelID());
            followingChannels.add(search.getInfluencerProfileByID());
        }
        ArrayList<String> interests = new ArrayList<>();
        interests.add("music");
        interests.add("movie");
        interests.add("nba");
        interests.add("cba");
        UserProfile user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests, followingChannels);
        return new FollowingView(user);
    }


    @Path("/home/{name}/{email}/{channelId}")
    @GET
    public InfluencerProfileView getInfluencerForUser(@PathParam("channelId") String channelId)
            throws GeneralSecurityException, IOException {
        Search search = new Search(channelId);
        ArrayList<String> links = search.getPopularVideoList();
        ArrayList<String> threeLinks = new ArrayList<>();
        System.out.println("Total of " + links.size() + " videos are return for channel: " + channelId);
        for (int i = 0; i < 5 && i < links.size(); ++i) {
            threeLinks.add("https://www.youtube.com/embed/" + links.get(i));
            //System.out.println("link is " + threeLinks.get(i));
        }
        InfluencerProfile curInfluencer = search.getInfluencerProfileByID();
        return new InfluencerProfileView(curInfluencer, threeLinks);
    }

    @Path("/home/{name}/{email}/recommendation")
    @GET
    @Timed
    @UnitOfWork
    public RecommendationView getRecommendationForUser(@PathParam("name") String name, @PathParam("email") String email)
            throws GeneralSecurityException, IOException {
        Search search = new Search("");
        ArrayList<String> interests = new ArrayList<>();
        List<UserProfile> userProfileList = userProfileDAO.getUserProfile(email);
        if(userProfileList.size() == 1){
            UserProfile curUser = userProfileList.get(0);
            ArrayList<String> curInterests = curUser.getInterests();
            for(String interest : curInterests){
                System.out.println("My interest is " + interest);
                interests.add(interest);
            }
            System.out.println("user name is " + curUser.getName());
            System.out.println("user email is " + curUser.getEmail());
        }

        ArrayList<String> channels = search.getRecommendedChannelID(interests);
        ArrayList<InfluencerProfile> influencerProfiles = new ArrayList<>();
        for(String channel : channels){
            System.out.println("recommendation is " + channel);
            Search searchProfile = new Search(channel);
            influencerProfiles.add(searchProfile.getInfluencerProfileByID());
        }
        UserProfile user = new UserProfile(name, email);
        ArrayList<String> followingChannelsID = new ArrayList<>();
        List<LikeRecord> records = likeRecordDAO.findAll(email);
        for(LikeRecord record : records){
            followingChannelsID.add(record.getChannelID());
        }
        return new RecommendationView(influencerProfiles, user, followingChannelsID);
    }

}