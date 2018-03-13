package com.onlinebank.repo;

import com.onlinebank.model.accounts.SavingAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SavingAccountRepo extends PagingAndSortingRepository<SavingAccount, Long> {
    List<SavingAccount> findByUser_Username(String username);
    SavingAccount findByIdAndUserUsername(long id, String username);
}
