package models;

import javax.persistence.*;

@Entity
@Table(name = "LikeRecord")
@NamedQueries(
        {
                @NamedQuery(
                        name = "models.findAllLikes",
                        query = "SELECT l FROM LikeRecord l WHERE l.email =:userEmail"
                ),
                @NamedQuery(
                        name = "models.deleteLikeRecord",
                        query = "DELETE FROM LikeRecord l WHERE l.email =:userEmail AND l.channelID =:influencerChannelID"
                )
        })

public class LikeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "channelID", nullable = false)
    private String channelID;

    public LikeRecord() {
    }
        
    /**
     * Constructor.
     *
     * @param email
     * the user's email
     * @param channelID
     * the id of the channel
     */
    public LikeRecord(String email, String channelID) {
        this.email = email;
        this.channelID = channelID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }
}
