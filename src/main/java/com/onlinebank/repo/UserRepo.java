package com.onlinebank.repo;

import com.onlinebank.model.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);
    User findByEmail(String email);
}
