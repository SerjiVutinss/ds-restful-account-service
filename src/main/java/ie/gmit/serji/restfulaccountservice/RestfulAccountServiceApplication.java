package ie.gmit.serji.restfulaccountservice;

import ie.gmit.serji.restfulaccountservice.grpc.GrpcPasswordClient;
import ie.gmit.serji.restfulaccountservice.health.TemplateHealthCheck;
import ie.gmit.serji.restfulaccountservice.resources.HelloWorldResource;
import ie.gmit.serji.restfulaccountservice.resources.LoginResource;
import ie.gmit.serji.restfulaccountservice.resources.UsersResource;
import ie.gmit.serji.restfulaccountservice.services.IPasswordService;
import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;
import ie.gmit.serji.restfulaccountservice.services.PasswordService;
import ie.gmit.serji.restfulaccountservice.services.UsersDbService;
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
    public void initialize(Bootstrap<RestfulAccountServiceConfiguration> bootstrap) { }

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


        // TODO: DI not working correctly - passing implementations here
        final IPasswordService passwordService = new PasswordService(GrpcPasswordClient.getInstance());
        final IUsersDbService usersDbService = new UsersDbService(passwordService);

        final UsersResource usersResource = new UsersResource(usersDbService);
        final LoginResource loginResource = new LoginResource(usersDbService, passwordService);

        environment.healthChecks().register("template", healthCheck);

//        environment.jersey().register(helloWorldResource);
        environment.jersey().register(usersResource);
        environment.jersey().register(loginResource);
    }

}

