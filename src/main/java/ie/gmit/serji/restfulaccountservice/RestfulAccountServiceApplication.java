package ie.gmit.serji.restfulaccountservice;

import ie.gmit.serji.restfulaccountservice.di.components.DaggerLoginResourceComponent;
import ie.gmit.serji.restfulaccountservice.di.components.DaggerUsersResourceComponent;
import ie.gmit.serji.restfulaccountservice.grpc.GrpcPasswordClient;
import ie.gmit.serji.restfulaccountservice.health.TemplateHealthCheck;
import ie.gmit.serji.restfulaccountservice.resources.LoginResource;
import ie.gmit.serji.restfulaccountservice.resources.UsersResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestfulAccountServiceApplication extends Application<RestfulAccountServiceConfiguration> {

    public static void main(String[] args) throws Exception {

        new RestfulAccountServiceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RestfulAccountServiceConfiguration> bootstrap) {
    }

    @Override
    public void run(RestfulAccountServiceConfiguration configuration,
                    Environment environment) {

        // Configure the GrpcPassword client with host and port
        GrpcPasswordClient.configure(
                configuration.getGrpcHost(),
                configuration.getGrpcPort()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
                configuration.getTemplate()
        );


        // Create Resources using Dagger for dependency injection
        final UsersResource usersResource = DaggerUsersResourceComponent.create().get();
        final LoginResource loginResource = DaggerLoginResourceComponent.create().get();

        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(usersResource);
        environment.jersey().register(loginResource);
    }

}

