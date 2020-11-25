package db;

import models.LikeRecord;
import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class LikeRecordDAO extends AbstractDAO<LikeRecord>{
    /**
     * Call parent constructor.
     */
    public LikeRecordDAO(SessionFactory factory) {
        super(factory);
    }
    
    /**
     * Change the like record by id. 
     */
    public Optional<LikeRecord> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * Create one like record. 
     */
    public LikeRecord create(LikeRecord record) {
        return persist(record);
    }

    /**
     * Read all like records by the user's email. 
     */
    @SuppressWarnings("unchecked")
    public List<LikeRecord> findAll(String email) {
        return list((Query<LikeRecord>) namedQuery("models.findAllLikes").
                setParameter("userEmail", email));
    }
}
