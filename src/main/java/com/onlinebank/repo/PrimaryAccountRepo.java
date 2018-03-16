package com.onlinebank.repo;

import com.onlinebank.model.accounts.PrimaryAccount;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface PrimaryAccountRepo extends AccountBaseRepo<PrimaryAccount> {
}
