package tasks;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.*;
import tasks.Auth;
import com.google.api.services.youtube.YouTube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Print a list of videos matching a search term.
 *
 * @author Jeremy Walker
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
    private static YouTube youtube;

    private String channelID;
    public Search(String channelID){
        this.channelID = channelID;
    }

    /**
     * Initialize a YouTube object to search for videos on YouTube. Then
     * display the name and thumbnail image of each video in the result set.
     *
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
            String apiKey = "AIzaSyAccsqGaUxwL9ht-arv7om2_RKQpXT_f70";

            // Define the API request for retrieving search results.
            //YouTube.Search.List search = youtube.search().list("id,snippet");
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
            for(int i=0; i<items.size() && i<6; ++i){
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
}
