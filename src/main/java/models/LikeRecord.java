package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "LikeRecord")
@NamedQueries(
        {
                @NamedQuery(
                        name = "models.findAllLikes",
                        query = "SELECT l FROM LikeRecord l WHERE l.email =:userEmail"
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
     * Constructor
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
