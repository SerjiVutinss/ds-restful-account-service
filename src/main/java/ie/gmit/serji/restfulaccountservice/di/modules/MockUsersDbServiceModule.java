package ie.gmit.serji.restfulaccountservice.di.modules;

import dagger.Binds;
import dagger.Module;
import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;
import ie.gmit.serji.restfulaccountservice.services.MockUsersDbService;

@Module
public abstract class MockUsersDbServiceModule {

    @Binds
    abstract IUsersDbService bind(MockUsersDbService mockUsersDbService);
}
