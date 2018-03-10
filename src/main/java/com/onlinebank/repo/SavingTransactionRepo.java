package com.onlinebank.repo;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.SavingTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SavingTransactionRepo extends PagingAndSortingRepository<SavingTransaction, Long>, JpaSpecificationExecutor<SavingTransaction> {
    List<SavingTransaction> findByAccount(Account account, Pageable pageRequest);
}
