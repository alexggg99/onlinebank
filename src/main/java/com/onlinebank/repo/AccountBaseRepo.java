package com.onlinebank.repo;

import com.onlinebank.model.accounts.Account;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface AccountBaseRepo <T extends Account> extends PagingAndSortingRepository<T, Long> {
    List<T> findByUserUsernameOrderById(String username);
    T findByIdAndUserUsername(long id, String username);
}
