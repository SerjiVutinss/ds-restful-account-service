package ie.gmit.serji.restfulaccountservice.di.components;

import dagger.Component;
import ie.gmit.serji.restfulaccountservice.resources.UsersResource;
import ie.gmit.serji.restfulaccountservice.di.modules.MockUsersDbServiceModule;
import ie.gmit.serji.restfulaccountservice.di.modules.GrpcPasswordServiceModule;

@Component (modules = {MockUsersDbServiceModule.class, GrpcPasswordServiceModule.class})
public interface UsersResourceComponent {

    UsersResource get();

}
