package ie.gmit.serji.restfulaccountservice;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestfulAccountServiceApplication extends Application<RestfulAccountServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new RestfulAccountServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<RestfulAccountServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(RestfulAccountServiceConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
    }

}