package com.onlinebank.repo;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.PrimaryTransaction;
import com.onlinebank.model.accounts.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PrimaryTransactionRepo extends PagingAndSortingRepository<PrimaryTransaction, Long> {
    List<Transaction> findByAccountAndAccount_UserUsername(Account account, String username, Pageable pageRequest);
}
