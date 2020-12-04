import db.InfluencerProfileDAO;
import db.LikeRecordDAO;
import db.UserProfileDAO;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import models.InfluencerProfile;
import models.LikeRecord;
import models.UserProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import resources.InfluencerBoardResource;
import views.FollowingView;
import views.InfluencerProfileView;
import views.UserHomeView;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link InfluencerBoardResource}.
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class InfluencerBoardResourceTest {

    @Mock
    private static UserProfileDAO userDAO;
    @Mock
    private static InfluencerProfileDAO influencerDAO;
    @Mock
    private static LikeRecordDAO likeRecordDAO;

    public static final ResourceExtension RULE = ResourceExtension.builder()
            .addResource(new InfluencerBoardResource(userDAO, influencerDAO, likeRecordDAO))
            .build();

    @InjectMocks
    private static InfluencerBoardResource resource;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void renew() {
        resource = new InfluencerBoardResource(userDAO, influencerDAO, likeRecordDAO);
    }

    @AfterEach
    public void tearDown() {
        reset(userDAO);
        reset(influencerDAO);
        reset(likeRecordDAO);
    }

    @Test
    public void getHomeForUserTestA() throws IOException {
        resource = new InfluencerBoardResource(userDAO, influencerDAO, likeRecordDAO);
        UserProfile user = new UserProfile("April", null);
        UserHomeView view = resource.getHomeForUser(user.getName(), user.getEmail());
        assertEquals(view, null);
        reset(userDAO);
        reset(influencerDAO);
        reset(likeRecordDAO);
    }

    @Test
    public void getHomeForUserTestB() {
        UserProfile user = new UserProfile("April", "april@gmail.com");
        try {
            UserHomeView view = resource.getHomeForUser(user.getName(), user.getEmail());
            assertEquals(view.getUserProfile().getName(), "April");
            assertEquals(view.getUserProfile().getEmail(), "april@gmail.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addLikeRecordTestA() {
        String email = "april@gmail.com";
        resource.addLikeRecord(null, email);
        List<LikeRecord> list = new ArrayList<>();
        when(likeRecordDAO.findAll(email)).thenReturn(list);
        assertEquals(list, resource.findLikeRecordByEmail(email));
    }

    @Test
    public void addLikeRecordTestB() {
        String email = "april@gmail.com";
        resource.addLikeRecord("1", email);
        List<LikeRecord> list = new ArrayList<>();
        LikeRecord lr = new LikeRecord(email, "1");
        list.add(lr);
        when(likeRecordDAO.findAll(email)).thenReturn(list);
    }

    @Test
    public void deleteLikeRecordTestA() {
        String email = "april@gmail.com";
        resource.addLikeRecord("1", email);
        resource.deleteLikeRecord(null, email);
        List<LikeRecord> list = new ArrayList<>();
        LikeRecord lr = new LikeRecord(email, "1");
        list.add(lr);
        when(likeRecordDAO.findAll(email)).thenReturn(list);
    }

    @Test
    public void deleteLikeRecordTestB() {
        String email = "april@gmail.com";
        resource.addLikeRecord("1", email);
        resource.deleteLikeRecord("2", email);
        List<LikeRecord> list = new ArrayList<>();
        LikeRecord lr = new LikeRecord(email, "1");
        list.add(lr);
        when(likeRecordDAO.findAll(email)).thenReturn(list);
    }

    @Test
    public void deleteLikeRecordTestC() {
        String email = "april@gmail.com";
        resource.addLikeRecord("1", email);
        resource.addLikeRecord("2", email);
        List<LikeRecord> list = new ArrayList<>();
        LikeRecord lr1 = new LikeRecord(email, "1");
        LikeRecord lr2 = new LikeRecord(email, "2");
        list.add(lr1);
        list.add(lr2);
        when(likeRecordDAO.findAll(email)).thenReturn(list);
        resource.deleteLikeRecord("1", email);
        list.remove(lr1);
        when(likeRecordDAO.findAll(email)).thenReturn(list);
    }

    @Test
    public void getFollowingTestA() throws IOException {
        String email = "april@gmail.com";
        resource.addLikeRecord("1", email);
        resource.addLikeRecord("2", email);
        FollowingView view = resource.getFollowing(null, email);
        assertEquals(null, view);
    }

    @Test
    public void getFollowingTestB() throws IOException {
        String email = "april@gmail.com";
        String name = "April";
        resource.addLikeRecord("1", email);
        resource.addLikeRecord("2", email);
        List<LikeRecord> list = new ArrayList<>();
        LikeRecord lr1 = new LikeRecord(email, "1");
        LikeRecord lr2 = new LikeRecord(email, "2");
        list.add(lr1);
        list.add(lr2);
        FollowingView view = resource.getFollowing(name, email);
        assertEquals(name, view.getUser().getName());
        assertEquals(email, view.getUser().getEmail());
    }

    @Test
    public void getInfluencerForUserTestA() throws IOException, GeneralSecurityException {
        InfluencerProfileView view = resource.getInfluencerForUser("UCw8ZhLPdQ0u_Y-TLKd61hGA");
        InfluencerProfile curInfluencer = view.getInfluencerProfile();
        ArrayList<String> links = view.getVideoLinks();
        assertEquals(curInfluencer.getChannelName(), "1MILLION Dance Studio");
        assertEquals(curInfluencer.getPhotoLink(), "https://yt3.ggpht.com/ytc/AAUvwnh1e9ZrKP58etpfsjLmktO74CwuMQgYrNNf-ERvTg=s800-c-k-c0x00ffffff-no-rj");
        assertEquals(curInfluencer.getChannelId(), "UCw8ZhLPdQ0u_Y-TLKd61hGA");
        assertEquals(curInfluencer.getAveragePostViews(), "2300");
        assertEquals(curInfluencer.getNumOfSubscribers(), "22700000");
        assertEquals(curInfluencer.getCountryCode(), "KR");
        assertEquals(links.get(0), "https://www.youtube.com/embed/SoXGx7FakyU");
        assertEquals(links.get(1), "https://www.youtube.com/embed/Vaz_kpmTi0M");
        assertEquals(links.get(2), "https://www.youtube.com/embed/OC6AFSZLtnk");
        assertEquals(links.get(3), "https://www.youtube.com/embed/exppHlhjp9k");
    }

    @Test
    public void getInfluencerForUserTestB() throws IOException, GeneralSecurityException {
        InfluencerProfileView view = resource.getInfluencerForUser("？？？");
        InfluencerProfile curInfluencer = view.getInfluencerProfile();
        ArrayList<String> links = view.getVideoLinks();
        assertEquals(curInfluencer.getChannelName(), "N/A");
        assertEquals(curInfluencer.getPhotoLink(), "N/A");
        assertEquals(curInfluencer.getChannelId(), "N/A");
        assertEquals(curInfluencer.getAveragePostViews(), "N/A");
        assertEquals(curInfluencer.getNumOfSubscribers(), "N/A");
        assertEquals(curInfluencer.getCountryCode(), "N/A");
        assertEquals(links.size(), 0);
    }
}
