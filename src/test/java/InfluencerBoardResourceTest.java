import org.junit.Test;
import resources.InfluencerBoardResource;
import views.LoginView;
import views.UserHomeView;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InfluencerBoardResourceTest {

    //@Test
    public void getWelcomeTest() {
        InfluencerBoardResource resource = new InfluencerBoardResource();
        LoginView loginView = resource.getWelcome();
        assertEquals(loginView.getWelcomeWords(), "Welcome to InfluencerBoard");
    }

//    @Test
//    public void getHomeForUserTest() throws IOException {
//        InfluencerBoardResource resource = new InfluencerBoardResource();
//        UserHomeView userHomeView = resource.getHomeForUser("April Wang", "wxuejing96@gmail.com");
//        assertEquals(userHomeView.getUserProfile().getName(), "April Wang");
//        assertEquals(userHomeView.getUserProfile().getEmail(), "wxuejing96@gmail.com");
//    }


//    @Test
//    public void getInfluencerForUserTest() throws IOException {
//        InfluencerBoardResource resource = new InfluencerBoardResource();
//        UserInfluencerProfileView userInfluencerProfileView = resource.getInfluencerForUser("April Wang", "wxuejing96@gmail.com", "blackpink", "Liked");
//        assertEquals(userInfluencerProfileView.getUserInfluencerProfile().getUser().getName(), "April Wang");
//        assertEquals(userInfluencerProfileView.getUserInfluencerProfile().getUser().getEmail(), "wxuejing96@gmail.com");
//        assertEquals(userInfluencerProfileView.getUserInfluencerProfile().getLiked(), "Liked");
//    }
}
