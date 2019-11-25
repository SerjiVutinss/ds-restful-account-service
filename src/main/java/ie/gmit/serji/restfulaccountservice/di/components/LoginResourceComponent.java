package ie.gmit.serji.restfulaccountservice.di.components;

import dagger.Component;
import ie.gmit.serji.restfulaccountservice.di.modules.GrpcPasswordServiceModule;
import ie.gmit.serji.restfulaccountservice.di.modules.MockUsersDbServiceModule;
import ie.gmit.serji.restfulaccountservice.resources.LoginResource;

@Component (modules = {GrpcPasswordServiceModule.class, MockUsersDbServiceModule.class})
public interface LoginResourceComponent {

    LoginResource get();

}
