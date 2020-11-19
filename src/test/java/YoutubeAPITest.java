import models.InfluencerProfile;
import org.junit.Test;
import tasks.Search;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class YoutubeAPITest {

    @Test
    public void getVideos(){
        Search search = new Search("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        ArrayList<String> links = search.getVideoList();
        ArrayList<String> threeLinks = new ArrayList<>();
        for(int i=0; i<3; ++i){
            threeLinks.add("https://www.youtube.com/embed/" + links.get(i));
        }
        assertEquals(threeLinks.get(0), "https://www.youtube.com/embed/fV54rTntWX4");
        assertEquals(threeLinks.get(1), "https://www.youtube.com/embed/rJtCdwm7YEE");
        assertEquals(threeLinks.get(2), "https://www.youtube.com/embed/1Qh39fXdauI");
    }

    @Test
    public void getInfluencerProfile(){
        Search search = new Search("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        InfluencerProfile curInfluencer = search.getInfluencerProfileByID();
        assertEquals(curInfluencer.getChannelName(), "Google Developers");
        assertEquals(curInfluencer.getChannelId(), "UC_x5XG1OV2P6uZZ5FSM9Ttw");
        assertEquals(curInfluencer.getPhotoLink(), "https://yt3.ggpht.com/a/AATXAJxlcOOJg-DqA-aiyQqGcP_1kZH0jJ3eIJWDPRsk88U=s800-c-k-c0x00ffffff-no-rj");
    }
}
