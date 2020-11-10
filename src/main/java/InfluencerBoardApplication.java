import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.InfluencerBoardResource;

public class InfluencerBoardApplication extends Application<InfluencerBoardConfiguration> {

    public static void main(String[] args) throws Exception {
        new InfluencerBoardApplication().run(args);
    }

    @Override
    public String getName(){
        return "hello world";
    }

    @Override
    public void initialize(Bootstrap<InfluencerBoardConfiguration> bootstrap) {
        // to be implemented
    }

    @Override
    public void run(InfluencerBoardConfiguration configuration, Environment environment) {
        final InfluencerBoardResource resource = new InfluencerBoardResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        environment.jersey().register(resource);
    }
}
