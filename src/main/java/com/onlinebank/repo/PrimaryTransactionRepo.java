package com.onlinebank.repo;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.PrimaryTransaction;
import com.onlinebank.model.accounts.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PrimaryTransactionRepo extends PagingAndSortingRepository<PrimaryTransaction, Long>, JpaSpecificationExecutor<PrimaryTransaction> {
    List<PrimaryTransaction> findByAccount(Account account, Pageable pageRequest);
}
