import models.InfluencerProfile;
import org.junit.Test;
import tasks.GetChannelAnalyticsTask;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GetChannelAnalyticsTaskTest {

    @Test
    public void getInfluencersTest(){
        GetChannelAnalyticsTask task = new GetChannelAnalyticsTask("/Users/chucheng/Desktop/CS4156/TeamProject/CS4156TeamProject/channelAnalytics.csv");
        ArrayList<InfluencerProfile> influencers = task.getInfluencers(6);
        assertEquals(influencers.get(0).getChannelId(), "UCw8ZhLPdQ0u_Y-TLKd61hGA");
        assertEquals(influencers.get(0).getChannelName(), "1MILLION Dance Studio");
        assertEquals(influencers.get(0).getCountryCode(), "KR");
        System.out.println(influencers.get(0).getTags());
        System.out.println(influencers.get(0).getAveragePostViews());
        System.out.println(influencers.get(0).getNumOfSubscribers());
        System.out.println(influencers.get(0).getPhotoLink());
    }

}
