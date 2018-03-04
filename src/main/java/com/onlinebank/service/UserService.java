package com.onlinebank.service;

import com.onlinebank.model.User;
import com.onlinebank.model.security.Role;
import com.onlinebank.model.security.UserRole;

import java.util.List;
import java.util.Set;

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
