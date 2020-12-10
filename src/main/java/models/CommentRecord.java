package models;

import javax.persistence.*;

@Entity
@Table(name = "CommentRecord")
@NamedQueries(
        {
                @NamedQuery(
                        name="getComments",
                        query="SELECT l FROM CommentRecord l WHERE l.channelId =: channelId"
                )
        })

public class CommentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "channelId", nullable = false)
    private String channelId;

    @Column(name = "comment", nullable = false)
    private String comment;

    public CommentRecord(String email, String channelId, String comment) {
        this.email = email;
        this.channelId = channelId;
        this.comment = comment;
    }

    public CommentRecord() {

    }

    public String getEmail() {
        return email;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getComment() {
        return comment;
    }
}
