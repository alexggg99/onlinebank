package com.onlinebank.repo;

import com.onlinebank.model.SavingAccount;
import com.onlinebank.model.User;
import org.springframework.data.repository.CrudRepository;

public interface SavingAccountRepo extends CrudRepository<SavingAccount, Long> {
}
