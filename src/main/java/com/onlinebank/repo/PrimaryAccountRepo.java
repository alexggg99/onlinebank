package com.onlinebank.repo;

import com.onlinebank.model.PrimaryAccount;
import com.onlinebank.model.User;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountRepo extends CrudRepository<PrimaryAccount, Long> {
}
