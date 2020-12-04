import db.InfluencerProfileDAO;
import db.LikeRecordDAO;
import db.UserProfileDAO;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
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
import views.UserHomeView;

import java.io.IOException;
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
        List<LikeRecord> list = new ArrayList<LikeRecord>();
        when(likeRecordDAO.findAll(email)).thenReturn(list);
    }

    @Test
    public void addLikeRecordTestB() {
        String email = "april@gmail.com";
        resource.addLikeRecord("1", email);
        List<LikeRecord> list = new ArrayList<LikeRecord>();
        LikeRecord lr = new LikeRecord(email, "1");
        list.add(lr);
        when(likeRecordDAO.findAll(email)).thenReturn(list);
    }
}
