package tasks;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class YoutubeAPI {
    private final String channelID;
    private static final String CLIENT_SECRETS = "/Users/chucheng/Desktop/CS4156/TeamProject/CS4156TeamProject/client_secret.json";
    private static final Collection<String> SCOPES =
            Arrays.asList("https://www.googleapis.com/auth/youtube.readonly");

    private static final String APPLICATION_NAME = "API code samples";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public YoutubeAPI(String channelID) {
        this.channelID = channelID;
    }

    /**
     * Create an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        InputStream in = YoutubeAPI.class.getResourceAsStream(CLIENT_SECRETS);
        FileInputStream fis = new FileInputStream(new File(CLIENT_SECRETS));

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(fis));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                        .build();
        Credential credential =
                new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        in.close();
        return credential;
    }

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize(httpTransport);
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public String getPlayListID()
            throws GeneralSecurityException, IOException {
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Playlists.List request = youtubeService.playlists()
                .list("snippet,contentDetails");
        PlaylistListResponse response = request.setChannelId(this.channelID)
                .setMaxResults(25L)
                .execute();
        System.out.println(response.getItems().get(0).getId());
        return response.getItems().get(0).getId();
    }

    public ArrayList<String> getVideoID()
            throws GeneralSecurityException, IOException {
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.PlaylistItems.List request = youtubeService.playlistItems()
                .list("snippet,contentDetails");
        String playListID = getPlayListID();
        PlaylistItemListResponse response = request.setPlaylistId(playListID)
                .execute();
        ArrayList<String> videoList = new ArrayList<>();
        List<PlaylistItem> items = response.getItems();
        for (int i = 0; i < items.size() && i < 5; ++i) {
            //System.out.println("Video id is "+(items.get(i).getSnippet().getResourceId().getVideoId()));
            videoList.add(items.get(i).getSnippet().getResourceId().getVideoId());
        }
        return videoList;
    }
}
