//package ie.gmit.serji.restfulaccountservice;
//
//import com.google.inject.AbstractModule;
//import ie.gmit.serji.restfulaccountservice.services.IPasswordService;
//import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;
//import ie.gmit.serji.restfulaccountservice.services.PasswordService;
//import ie.gmit.serji.restfulaccountservice.services.UsersDbService;
//
//public class RestfulAccountServiceDI extends AbstractModule {
//    @Override
//    protected void configure() {
//
//        /*
//        Set up classes for DI
//         */
//
//        // IPasswordClientService - communication with gRPC client
//        bind(IPasswordService.class).to(PasswordService.class);
//        // IUsersDbService - data store for User objects
//        bind(IUsersDbService.class).to(UsersDbService.class);
//
//    }
//}
