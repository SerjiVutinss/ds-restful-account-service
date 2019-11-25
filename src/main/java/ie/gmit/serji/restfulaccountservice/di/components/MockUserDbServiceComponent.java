package ie.gmit.serji.restfulaccountservice.di.components;

import dagger.Component;
import ie.gmit.serji.restfulaccountservice.services.MockUsersDbService;
import ie.gmit.serji.restfulaccountservice.di.modules.GrpcPasswordServiceModule;

@Component (modules = {GrpcPasswordServiceModule.class})
public interface MockUserDbServiceComponent {

    MockUsersDbService get();

}
