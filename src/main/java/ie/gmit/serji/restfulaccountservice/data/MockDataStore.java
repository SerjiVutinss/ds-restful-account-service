package ie.gmit.serji.restfulaccountservice.data;

import ie.gmit.serji.restfulaccountservice.api.User;

import java.util.HashMap;
import java.util.Map;

/*
Singleton class mocking a data store

 */
public class MockDataStore {

    private static MockDataStore _instance;

    public static MockDataStore getInstance() {
        if (_instance == null) _instance = new MockDataStore();
        return _instance;
    }

    private Map<Integer, User> _users;

    private MockDataStore() {
        _users = new HashMap<Integer, User>();
    }

    public Map<Integer, User> getUsersDataStore() {
        return _users;
    }
}
