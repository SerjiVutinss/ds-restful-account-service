package ie.gmit.serji.restfulaccountservice.services;

import ie.gmit.serji.restfulaccountservice.api.User;

public interface IPasswordClientService {

    User hashPassword(User user);

    public boolean validateUser(User user, String password);

}
