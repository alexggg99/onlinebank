package com.onlinebank.service;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.Currency;
import com.onlinebank.model.accounts.PrimaryAccount;
import com.onlinebank.model.accounts.SavingAccount;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts(String username);
    PrimaryAccount createPrimaryAccount(Currency currency);
    SavingAccount createSavingAccount();
    Account getPrimaryAccount(long accountId, String username);
    Account getSavingAccount(long accountId, String username);
    boolean depositMoney(Account account, BigDecimal amount);
}
