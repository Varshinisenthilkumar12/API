package com.job.services;
import java.util.HashMap;
import java.util.Map;
import com.job.model.User;

public class UserService {
    private Map<String, User> userDatabase;

    public UserService() {
        userDatabase = new HashMap<>();
    }

    public void registerUser(User user) {
       
        if (userDatabase.containsKey(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        userDatabase.put(user.getUsername(), user);
    }
    public User loginUser(String username, String password) {
        
        if (!userDatabase.containsKey(username)) {
            throw new RuntimeException("Username not found");
        }

        User user = userDatabase.get(username);
       
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    public User getUserDetails(String username) {
     
        if (!userDatabase.containsKey(username)) {
            return null;
        }

        return userDatabase.get(username);
    }
}

