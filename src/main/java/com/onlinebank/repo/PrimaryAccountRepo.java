package com.onlinebank.repo;

import com.onlinebank.model.accounts.PrimaryAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrimaryAccountRepo extends PagingAndSortingRepository<PrimaryAccount, Long> {
    PrimaryAccount findByIdAndUserUsername(long id, String username);
}
