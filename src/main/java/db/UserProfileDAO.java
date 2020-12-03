package db;

import io.dropwizard.hibernate.AbstractDAO;
import models.UserProfile;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserProfileDAO extends AbstractDAO<UserProfile> {

    public UserProfileDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * create a user profile entry in db
     * @param profile
     * @return an UserProfile object being added to db
     */
    public UserProfile create(UserProfile profile) {
        return persist(profile);
    }

    public List<UserProfile> getAll() {
        return list((Query<UserProfile>) namedQuery("getAllUsers"));
    }

/*    public int updateUser(UserProfile user) {

    }*/
}
