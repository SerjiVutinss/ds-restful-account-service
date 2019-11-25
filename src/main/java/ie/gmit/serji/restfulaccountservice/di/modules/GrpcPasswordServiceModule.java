package ie.gmit.serji.restfulaccountservice.di.modules;

import dagger.Binds;
import dagger.Module;
import ie.gmit.serji.restfulaccountservice.services.IPasswordService;
import ie.gmit.serji.restfulaccountservice.services.GrpcPasswordService;

@Module
public abstract class GrpcPasswordServiceModule {

    @Binds
    abstract IPasswordService bind(GrpcPasswordService grpcPasswordService);

}
