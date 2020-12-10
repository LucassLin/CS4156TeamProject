import db.CommentRecordDAO;
import db.LikeRecordDAO;
import db.UserProfileDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import models.CommentRecord;
import models.LikeRecord;
import models.UserProfile;
import resources.InfluencerBoardResource;

import java.util.Map;

public class InfluencerBoardApplication extends Application<InfluencerBoardConfiguration> {

    public static void main(String[] args) throws Exception {
        new InfluencerBoardApplication().run(args);
    }

    private final HibernateBundle<InfluencerBoardConfiguration> hibernateBundle =
            new HibernateBundle<InfluencerBoardConfiguration>(LikeRecord.class, UserProfile.class, CommentRecord.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(InfluencerBoardConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    /**
     * Initialize the bootstrap.
     */
    @Override
    public void initialize(Bootstrap<InfluencerBoardConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<InfluencerBoardConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(InfluencerBoardConfiguration config) {
                return config.getViewRendererConfiguration();
            }
        });
        bootstrap.addBundle(new MigrationsBundle<InfluencerBoardConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(InfluencerBoardConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(InfluencerBoardConfiguration configuration, Environment environment) {
        final LikeRecordDAO likeRecordDAO = new LikeRecordDAO(hibernateBundle.getSessionFactory());
        final UserProfileDAO userProfileDAO = new UserProfileDAO(hibernateBundle.getSessionFactory());
        final CommentRecordDAO commentRecordDAO = new CommentRecordDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new InfluencerBoardResource(userProfileDAO, likeRecordDAO, commentRecordDAO));
    }
}
