import models.InfluencerProfile;
import org.junit.Test;
import tasks.GetChannelAnalyticsTask;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GetChannelAnalyticsTaskTest {

    @Test
    public void getInfluencersTestA() throws IOException {
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/xuejing/Desktop/Fall 2020/cloud computing/CS4156TeamProject/src/main/resources/data/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(-1);
        assertEquals(influencers.size(), 0);
    }

    @Test
    public void getInfluencersTestB() throws IOException {
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/xuejing/Desktop/Fall 2020/cloud computing/CS4156TeamProject/src/main/resources/data/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(6);
        assertEquals(influencers.size(), 6);
        influencers = task.getInfluencers(0);
        assertEquals(influencers.size(), 0);
    }


}
