import models.InfluencerProfile;
import org.junit.Test;
import tasks.Search;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class YoutubeAPITest {

    @Test
    public void getInfluencerProfileValidTest() {
        Search search = new Search("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        InfluencerProfile curInfluencer = search.getInfluencerProfileByID();
        assertEquals(curInfluencer.getChannelName(), "Google Developers");
        assertEquals(curInfluencer.getChannelId(), "UC_x5XG1OV2P6uZZ5FSM9Ttw");
        assertEquals(curInfluencer.getPhotoLink(), "https://yt3.ggpht.com/ytc/AAUvwngOju7AKiAvKEs1wtsZN366tyNPyMq3nD8eFkMF7bE=s800-c-k-c0x00ffffff-no-rj");
    }

    @Test
    public void getInfluencerProfileInvalidTest() {
        Search search = new Search("???");
        InfluencerProfile curInfluencer = search.getInfluencerProfileByID();
        ArrayList<String> dummyTags = new ArrayList<>();
        InfluencerProfile dummy = new InfluencerProfile("N/A", "N/A",
                "N/A", "N/A", "N/A", "N/A", dummyTags,
                "N/A");
        assertEquals(curInfluencer.getChannelName(), "N/A");
        assertEquals(curInfluencer.getPhotoLink(), "N/A");
        assertEquals(curInfluencer.getChannelId(), "N/A");
        assertEquals(curInfluencer.getAveragePostViews(), "N/A");
        assertEquals(curInfluencer.getNumOfSubscribers(), "N/A");
        assertEquals(curInfluencer.getCountryCode(), "N/A");
    }

    @Test
    public void getPopularVideoListValidIdTest(){
        Search search = new Search("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        ArrayList<String> res = search.getPopularVideoList();
        assertEquals(res.get(0), "rDZ1AjDJjFI");
    }

    @Test
    public void getPopularVideoListInvalidIdTest(){
        Search search = new Search("???");
        ArrayList<String> res = search.getPopularVideoList();
        ArrayList<String> dummy = new ArrayList<>();
        assertEquals(res, dummy);
    }
}
