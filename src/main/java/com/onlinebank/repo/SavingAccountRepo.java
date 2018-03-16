package com.onlinebank.repo;

import com.onlinebank.model.accounts.SavingAccount;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SavingAccountRepo extends AccountBaseRepo<SavingAccount> {
}
