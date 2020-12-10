package db;

import io.dropwizard.hibernate.AbstractDAO;
import models.CommentRecord;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CommentRecordDAO extends AbstractDAO<CommentRecord> {

    public CommentRecordDAO(SessionFactory factory) {
        super(factory);
    }

    public CommentRecord create(CommentRecord record) {
        return persist(record);
    }

    public List<CommentRecord> getComments( String channelId) {
        return list((Query<CommentRecord>) namedQuery("getComments")
                .setParameter("channelId", channelId));
    }

}
