package db;

import io.dropwizard.hibernate.AbstractDAO;
import models.LikeRecord;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class LikeRecordDAO extends AbstractDAO<LikeRecord> {
    public LikeRecordDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<LikeRecord> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public LikeRecord create(LikeRecord record) {
        return persist(record);
    }

    public List<LikeRecord> findAll(String email) {
        return list((Query<LikeRecord>) namedQuery("models.findAllLikes").
                setParameter("userEmail", email));
    }

    public int deleteRecord(String email, String channelID) {
        Query query = namedQuery("models.deleteLikeRecord").
                setParameter("userEmail", email).
                setParameter("influencerChannelID", channelID);
        return query.executeUpdate();
    }
}
