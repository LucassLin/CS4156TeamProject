import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import resources.InfluencerBoardResource;

import java.util.Map;

public class InfluencerBoardApplication extends Application<InfluencerBoardConfiguration> {

    public static void main(String[] args) throws Exception {
        new InfluencerBoardApplication().run(args);
    }

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
    }

    @Override
    public void run(InfluencerBoardConfiguration configuration, Environment environment) {
        final InfluencerBoardResource resource = new InfluencerBoardResource();
        environment.jersey().register(resource);
    }
}
