package com.onlinebank.repo;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TransactionRepo extends PagingAndSortingRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    List<Transaction> findByAccountAndAccountUserUsername(Account account, String username, Pageable pageRequest);
}
