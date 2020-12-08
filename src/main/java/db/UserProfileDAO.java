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
     *
     * @param profile
     * @return an UserProfile object being added to db
     */
    public UserProfile createUser(UserProfile profile) {
        if (profile.getEmail() == null || profile.getName() == null) {
            return null;
        }
        return persist(profile);
    }

    public List<UserProfile> getAllUsers() {
        return list((Query<UserProfile>) namedQuery("getAllUsers"));
    }

    public List<UserProfile> getUserProfile(String email) {
        return list((Query<UserProfile>) namedQuery("getUserProfile")
                .setParameter("userEmail", email));
    }

    public int deleteUserProfile(String email) {
        Query query = namedQuery("deleteUserProfile").
                setParameter("userEmail", email);
        return query.executeUpdate();
    }

    public int updateUserProfile(String email, String newInterests) {
        Query query = namedQuery("updateUserProfile")
                .setParameter("userEmail", email)
                .setParameter("interestsString", newInterests);
        return query.executeUpdate();
    }
}
