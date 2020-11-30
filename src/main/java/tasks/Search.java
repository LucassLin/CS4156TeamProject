package tasks;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import models.InfluencerProfile;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Print a list of videos matching a search term.
 */
public class Search {

    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private YouTube youtube;
    private final String apiKey = "";

    private final String channelID;
    
    /**
     * Constructor.
     *
     * @param channelID
     * the id of the channel
     */
    public Search(String channelID) {
        this.channelID = channelID;
    }

    public InfluencerProfile getInfluencerProfileByID() {
        try {
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            YouTube.Channels.List request = youtube.channels()
                    .list("snippet,contentDetails,statistics");
            request.setKey(apiKey);
            ChannelListResponse response = request.setId(this.channelID).execute();
            InfluencerProfile influencerProfile;
            if (response.getItems().size() == 0) {
                System.out.println("No channel match the ID");
            }
            ArrayList<String> tags = new ArrayList<>();
            Channel item = response.getItems().get(0);
            ChannelSnippet snippet = item.getSnippet();
            influencerProfile = new InfluencerProfile(item.getId(), snippet.getTitle(),
                    "No Type", snippet.getCountry(),
                    String.valueOf(item.getStatistics().getSubscriberCount()),
                    String.valueOf(item.getStatistics().getVideoCount()), tags,
                    String.valueOf(snippet.getThumbnails().getHigh().getUrl()),
                    snippet.getDescription());
            return influencerProfile;

        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        ArrayList<String> dummyTags = new ArrayList<>();
        InfluencerProfile dummy = new InfluencerProfile("N/A", "N/A",
                "N/A", "N/A", "N/A", "N/A", dummyTags,
                "N/A");
        return dummy;
    }

    /**
     * Initialize a YouTube object to search for videos on YouTube. Then
     * display the name and thumbnail image of each video in the result set.
     */
    public ArrayList<String> getVideoList() {
        // Read the developer key from the properties file.
        try {
            // This objecjat is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Prompt the user to enter a query term.

            YouTube.Playlists.List request = youtube.playlists()
                    .list("snippet,contentDetails");
            request.setKey(apiKey);
            PlaylistListResponse response = request.setChannelId(this.channelID)
                    .setMaxResults(25L)
                    .execute();
            String playListID = response.getItems().get(0).getId();

            YouTube.PlaylistItems.List requestVideos = youtube.playlistItems()
                    .list("snippet,contentDetails");
            requestVideos.setKey(apiKey);
            PlaylistItemListResponse responseVideos = requestVideos.setPlaylistId(playListID)
                    .execute();
            ArrayList<String> videoList = new ArrayList<>();
            List<PlaylistItem> items = responseVideos.getItems();
            for (int i = 0; i < items.size() && i < 6; ++i) {
                videoList.add(items.get(i).getSnippet().getResourceId().getVideoId());
            }
//            for(int i=0; i<videoList.size(); ++i){
//                System.out.println("video id is " + videoList.get(i));
//            }
            return videoList;

        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        ArrayList<String> empty = new ArrayList<>();
        return empty;
    }

    public ArrayList<String> getRandomChannelID(){
        try {
            // This objecjat is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Prompt the user to enter a query term.

            YouTube.Search.List request = youtube.search()
                    .list("snippet");

            request.setKey(apiKey);
            SearchListResponse response = request.setMaxResults(100L)
                    .setOrder("viewCount")
                    .setType("channel")
                    .execute();

            ArrayList<String> channelID = new ArrayList<>();
            for(SearchResult item : response.getItems()){
                channelID.add(item.getId().getChannelId());
            }
            Collections.shuffle(channelID);

            ArrayList<String> eightRandom = new ArrayList<>();
            for(int i=0; i<8; ++i){
                eightRandom.add(channelID.get(i));
            }

            return eightRandom;

        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        ArrayList<String> empty = new ArrayList<>();
        return empty;
    }

    public ArrayList<String> getPopularVideoList() {
        // Read the developer key from the properties file.
        try {
            // This objecjat is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            ArrayList<String> videos = new ArrayList<>();

            YouTube.Search.List request = youtube.search()
                    .list("snippet");
            request.setKey(apiKey);
            SearchListResponse response = request.setMaxResults(25L)
                    .setChannelId(this.channelID)
                    .setOrder("viewCount")
                    .setType("video")
                    .execute();
            List<SearchResult> items = response.getItems();
            for(SearchResult res : items){
                videos.add(res.getId().getVideoId());
            }
            return videos;

        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        ArrayList<String> empty = new ArrayList<>();
        return empty;
    }
}
