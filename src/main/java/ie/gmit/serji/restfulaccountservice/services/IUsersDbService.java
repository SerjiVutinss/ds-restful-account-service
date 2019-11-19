package ie.gmit.serji.restfulaccountservice.services;

import ie.gmit.serji.restfulaccountservice.api.User;

import java.util.List;

public interface IUsersDbService {

    // get all users
    List<User> getAll();

    // get a single user by userId
    User getOne(Integer id);

    // add and return a new user
    Integer insert(User u, String password);

    // update an existing user
    User update(User u, String password);

    // delete an existing user
    User remove(int id);

    // Find a user by email
    User findByEmail(String email);

}
