package com.onlinebank.repo;

import com.onlinebank.model.accounts.Account;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface AccountRepo extends AccountBaseRepo<Account> {
}
