package com.onlinebank.service.impl;

import com.onlinebank.model.security.User;
import com.onlinebank.model.security.Role;
import com.onlinebank.model.security.UserRole;
import com.onlinebank.repo.UserRepo;
import com.onlinebank.service.AccountService;
import com.onlinebank.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AccountService accountService;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public boolean checkUserExist(String username, String email) {
        if(userRepo.findByUsernameOrEmail(username, email) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUsernameExist(String username) {
        if(findByUsername(username) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmailExist(String email) {
        if(findByEmail(email) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    @Transactional
    public User createUser(User user, Role role) {
        User localUser = userRepo.findByUsername(user.getUsername());

        if (localUser != null) {
            LOGGER.info("User " + user.getUsername() + "exists");
        } else {
            String encryptedPassword = encoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            localUser = userRepo.save(user);

            UserRole userRole = new UserRole(localUser, role);
            localUser.getUserRoleSet().add(userRole);
        }
        return localUser;
    }
}
