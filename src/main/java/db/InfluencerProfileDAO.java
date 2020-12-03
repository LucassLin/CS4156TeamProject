package db;

import io.dropwizard.hibernate.AbstractDAO;
import models.InfluencerProfile;
import org.hibernate.SessionFactory;

public class InfluencerProfileDAO extends AbstractDAO<InfluencerProfile> {

    public InfluencerProfileDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
