import models.InfluencerProfile;
import org.junit.Test;
import resources.InfluencerBoardResource;
import tasks.YoutubeAPI;
import views.InfluencerProfileView;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.Assert.assertEquals;

public class InfluencerProfileTest {

    @Test
    public void getInfluencerProfile() throws GeneralSecurityException, IOException {
        InfluencerBoardResource resource = new InfluencerBoardResource();
        InfluencerProfileView view = resource.getInfluencerForUser("UCw8ZhLPdQ0u_Y-TLKd61hGA");
        InfluencerProfile profile = view.getInfluencerProfile();
        assertEquals(profile.getChannelId(), "UCw8ZhLPdQ0u_Y-TLKd61hGA");
        assertEquals(profile.getChannelName(), "1MILLION Dance Studio");
        assertEquals(profile.getCountryCode(), "KR");

        YoutubeAPI api = new YoutubeAPI("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        api.getVideoID();
    }
}
