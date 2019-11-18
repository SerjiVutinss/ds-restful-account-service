package ie.gmit.serji.restfulaccountservice.services;

import ie.gmit.serji.restfulaccountservice.api.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Mock a Users Database using a Map
 */
public class UsersDbService implements IUsersDbService {

    private static UsersDbService _instance;

    public static UsersDbService getInstance() {
        if(_instance==null) _instance = new UsersDbService();
        return _instance;
    }

    private Map<Integer, User> _users;

    private UsersDbService() {
        _users = new HashMap<>();
        seedDb();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(_users.values());
    }

    @Override
    public User getOne(Integer id) {

        if (_users.containsKey(id)) {
            return _users.get(id);
        }
        return null;
    }

    @Override
    public User insert(User u) {
        int id = getNewKey();
        return _users.put(id, u);
    }

    @Override
    public User update(int id, User u) {
        if (_users.containsKey(id)) {
            return _users.put(id, u);
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

        User u1 = new User(1, "JohnDoe", "johndoe@example.com", "Hello123!", "mockhashed", "mocksalt");
        insert(u1);

        User u2 = new User(2, "JaneDoe", "janedoe@example.com", "Hello123!", "mockhashed", "mocksalt");
        insert(u2);

        User u3 = new User(3, "JohnSmith", "johnsmith@example.com", "Hello123!", "mockhashed", "mocksalt");
        insert(u3);

        User u4 = new User(4, "JimJohnson", "jimjohnson@example.com", "Hello123!", "mockhashed", "mocksalt");
        insert(u4);

        User u5 = new User(5, "MaryMurphy", "marymurphy@example.com", "Hello123!", "mockhashed", "mocksalt");
        insert(u5);

    }
}
