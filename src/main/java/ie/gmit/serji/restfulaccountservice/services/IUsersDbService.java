package ie.gmit.serji.restfulaccountservice.services;

import ie.gmit.serji.restfulaccountservice.api.User;

import java.util.List;

public interface IUsersDbService {

    // get all users
    List<User> getAll();

    // get a single user by userId
    User getOne(int id);

    // add and return a new user
    User insert(User u, String password);

    // update an existing user
    User update(int userId, User u, String password);

    // delete an existing user
    User remove(int id);

    // Find a user by email
    User findByEmail(String email);

}
