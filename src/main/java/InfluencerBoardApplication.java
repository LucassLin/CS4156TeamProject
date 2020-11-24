import db.LikeRecordDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import models.LikeRecord;
import org.jdbi.v3.core.Jdbi;
import resources.InfluencerBoardResource;
import resources.LikeRecordResource;

import java.util.Map;

public class InfluencerBoardApplication extends Application<InfluencerBoardConfiguration> {

    public static void main(String[] args) throws Exception {
        new InfluencerBoardApplication().run(args);
    }

    private final HibernateBundle<InfluencerBoardConfiguration> hibernateBundle =
            new HibernateBundle<InfluencerBoardConfiguration>(LikeRecord.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(InfluencerBoardConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "hello world";
    }

    @Override
    public void initialize(Bootstrap<InfluencerBoardConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<InfluencerBoardConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(InfluencerBoardConfiguration config) {
                return config.getViewRendererConfiguration();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new MigrationsBundle<InfluencerBoardConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(InfluencerBoardConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(InfluencerBoardConfiguration configuration, Environment environment) {
        final LikeRecordDAO dao = new LikeRecordDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new LikeRecordResource(dao));
        final InfluencerBoardResource resource = new InfluencerBoardResource();
        environment.jersey().register(resource);
    }
}
