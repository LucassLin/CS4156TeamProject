package tasks;

import models.InfluencerProfile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GetChannelAnalyticsTask {

    private final String srcFile;
    
    /**
     * Constructor.
     * @param srcFile
     * the source file
     */
    public GetChannelAnalyticsTask(String srcFile) {
        this.srcFile = srcFile;
    }
    
    /**
     * Get an array of influencers.
     * @param num an int number
     * @return ArrayList<> influencers
     */
    public ArrayList<InfluencerProfile> getInfluencers(int num) throws IOException {
        ArrayList<InfluencerProfile> influencers = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream(srcFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            int i = 0;
            while (i <= num) {
                i += 1;
                line = br.readLine();
                if (line != null) {
                    String[] values = line.split("\t");
                    String channelId = values[0];
                    String channelName = values[1];
                    String type = "YouTube";
                    String countryCode = values[3];
                    String numOfSubscribers = values[7];
                    String averagePostViews = values[6];
                    String[] preTags = values[2].substring(1, values[2].length() - 1).split(",");
                    ArrayList<String> tags = new ArrayList<>();
                    for (String tag : preTags) {
                        tag = tag.replaceAll("[ ']", "");
                        tags.add(tag);
                    }
                    String photoLink = values[8];
                    InfluencerProfile influencer = new InfluencerProfile(channelId, channelName, type, countryCode, numOfSubscribers, averagePostViews, tags, photoLink);
                    influencers.add(influencer);
                } else {
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        influencers.remove(0);
        return influencers;
    }
}

