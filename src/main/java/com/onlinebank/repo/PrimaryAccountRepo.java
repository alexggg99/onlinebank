package com.onlinebank.repo;

import com.onlinebank.model.accounts.PrimaryAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PrimaryAccountRepo extends PagingAndSortingRepository<PrimaryAccount, Long> {
    List<PrimaryAccount> findByUser_Username(String username);
    PrimaryAccount findByIdAndUserUsername(long id, String username);
}
