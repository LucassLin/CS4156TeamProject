package db;

import models.LikeRecord;
import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class LikeRecordDAO extends AbstractDAO<LikeRecord>{
    public LikeRecordDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<LikeRecord> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public LikeRecord create(LikeRecord record) {
        return persist(record);
    }

    @SuppressWarnings("unchecked")
    public List<LikeRecord> findAll(String email) {
        return list((Query<LikeRecord>) namedQuery("models.findAllLikes").
                setParameter("userEmail", email));
    }
}
