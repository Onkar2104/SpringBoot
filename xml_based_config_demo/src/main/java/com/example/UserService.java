package com.example;

import java.util.List;

public class UserService {

    private List<String> usernames;

    public UserService(List<String> usernames) {
        this.usernames = usernames;
        System.out.println("User Created");
    }

    public List<String> getUsernames() {
        return usernames;
    }
}
