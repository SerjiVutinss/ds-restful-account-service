package ie.gmit.serji.restfulaccountservice;

import ie.gmit.serji.restfulaccountservice.health.TemplateHealthCheck;
import ie.gmit.serji.restfulaccountservice.resources.HelloWorldResource;
import ie.gmit.serji.restfulaccountservice.resources.UsersResource;
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

        final HelloWorldResource helloWorldResource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
                configuration.getTemplate()
        );

        final UsersResource usersResource = new UsersResource();

        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(helloWorldResource);
        environment.jersey().register(usersResource);
    }

}