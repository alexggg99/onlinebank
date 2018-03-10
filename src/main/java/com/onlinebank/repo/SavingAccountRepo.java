package com.onlinebank.repo;

import com.onlinebank.model.accounts.SavingAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SavingAccountRepo extends PagingAndSortingRepository<SavingAccount, Long> {
    SavingAccount findByIdAndUserUsername(long id, String username);
}
