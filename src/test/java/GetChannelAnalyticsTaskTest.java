import models.InfluencerProfile;
import org.junit.Test;
import tasks.GetChannelAnalyticsTask;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GetChannelAnalyticsTaskTest {

    @Test
    public void getInfluencersTestA() throws IOException {
        String projDir = System.getProperty("user.dir");
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask(projDir+"/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(-1);
        assertEquals(influencers.size(), 0);
    }

    @Test
    public void getInfluencersTestB() throws IOException {
        String projDir = System.getProperty("user.dir");
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask(projDir+"/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(6);
        assertEquals(influencers.size(), 6);
        influencers = task.getInfluencers(0);
        assertEquals(influencers.size(), 0);
    }

    @Test
    public void getInfluencersTestC() throws IOException {
        String projDir = System.getProperty("user.dir");
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask(projDir+"/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(0);
        assertEquals(influencers.size(), 0);
    }

}
