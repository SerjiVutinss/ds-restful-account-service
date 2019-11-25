package ie.gmit.serji.restfulaccountservice.services;

import ie.gmit.serji.restfulaccountservice.api.UpsertUser;
import ie.gmit.serji.restfulaccountservice.api.User;
import ie.gmit.serji.restfulaccountservice.data.MockDataStore;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
Mock Database operations
 */
public class MockUsersDbService implements IUsersDbService {

    private Map<Integer, User> _users;

    private IPasswordService _passwordService;

    @Inject
    public MockUsersDbService(IPasswordService passwordService) {
        _passwordService = passwordService;

        System.out.println("CREATING DB");
        _users = MockDataStore.getInstance().getUsersDataStore();

        if (_users.size() == 0) seedDb();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(_users.values());
    }

    @Override
    public User getOne(int id) {

        if (_users.containsKey(id)) {
            return _users.get(id);
        }
        return null;
    }

    @Override
    public User insert(User user, String password) {
        // generate a new key for this user and set it
        user.setUserId(getNewKey());
        // generate hash and salt and set on this object
        byte[][] result = _passwordService.hashPassword(user.getUserId(), password);
        // now create a User object from the UpsertUser
        // set its hashedPassword and salt values
        user.setHashedPassword(result[0]);
        user.setSalt(result[1]);
        // put the object in the map
        _users.put(user.getUserId(), user);

        return user;
    }

    @Override
    public User update(int userId, User user, String password) {
        if (_users.containsKey(userId)) {

            byte[][] result = _passwordService.hashPassword(userId, password);
            user.setHashedPassword(result[0]);
            user.setSalt(result[1]);
            user.setUserId(userId);

            _users.put(userId, user);

            return _users.get(user.getUserId());
        }
        return null;
    }

    @Override
    public User remove(int id) {
        if (_users.containsKey(id)) {
            return _users.remove(id);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {

        for (User u : _users.values()) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    /*
    Mock generate new Primary Key - userId
        - this may cause issues if multiple clients are inserting
     */
    private int getNewKey() {
        int max = 0;
        for (Integer i : _users.keySet()) {
            if (i > max) max = i;
        }
        return max + 1;
    }


    /*
   Mock seeding the 'database'
    */
    private void seedDb() {

        UpsertUser u1 = new UpsertUser(1, "JohnDoe", "johndoe@example.com", "Hello123!");
        insert(new User(u1), u1.getPassword());

        UpsertUser u2 = new UpsertUser(2, "JaneDoe", "janedoe@example.com", "Hello123!");
        insert(new User(u2), u2.getPassword());

        UpsertUser u3 = new UpsertUser(3, "JohnSmith", "johnsmith@example.com", "Hello123!");
        insert(new User(u3), u3.getPassword());

        UpsertUser u4 = new UpsertUser(4, "JimJohnson", "jimjohnson@example.com", "Hello123!");
        insert(new User(u4), u4.getPassword());

        UpsertUser u5 = new UpsertUser(5, "MaryMurphy", "marymurphy@example.com", "Hello123!");
        insert(new User(u5), u5.getPassword());

    }
}
