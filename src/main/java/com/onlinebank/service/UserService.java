package com.onlinebank.service;

import com.onlinebank.model.security.User;
import com.onlinebank.model.security.Role;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkUserExist(String username, String email);
    boolean checkUsernameExist(String username);
    boolean checkEmailExist(String username);
    void saveUser(User user);
    User createUser(User user, Role role);
}
