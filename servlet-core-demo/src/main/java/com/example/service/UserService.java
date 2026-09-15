package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.User;

public class UserService {

    private Map<Integer, User> userDB;

    public UserService() {
        userDB = new HashMap<>();
    }

    public User createUser(User userReq) {
        userDB.put(userReq.getId(), userReq);
        return userReq;
    }

    public List<User> getAllUsers() {
        List<User> userResp = new ArrayList<>();
        for(User user : userDB.values()) {
            userResp.add(user);
        }

        return userResp;
    }

    public User getUserById(Integer id) {
        return userDB.getOrDefault(id, null);
    }
}
