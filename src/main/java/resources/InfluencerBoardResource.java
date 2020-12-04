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
import views.FollowingView;
import views.InfluencerProfileView;
import views.LoginView;
import views.UserHomeView;

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
    @Path("/LikeRecord/addRecord/{email}/{channelId}")
    public void addLikeRecord(@PathParam("channelId") String channelId, @PathParam("email") String email) {
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
        if (!existed) {
            userProfileDAO.createUser(user);
            System.out.println("new user added to db: " + user.getName() + ", " + user.getEmail());
        }
        // get recommendations for the current user
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/chucheng/Desktop/CS4156/TeamProject/CS4156TeamProject/channelAnalytics.csv");
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
        user = new UserProfile("01", name, email, "0000000000", "female", 19, "China", interests, followingChannels);

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

}