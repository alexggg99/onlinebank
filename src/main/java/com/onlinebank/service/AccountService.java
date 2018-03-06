package com.onlinebank.service;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.Currency;
import com.onlinebank.model.accounts.PrimaryAccount;
import com.onlinebank.model.accounts.SavingAccount;

public interface AccountService {
    PrimaryAccount createPrimaryAccount(Currency currency);
    SavingAccount createSavingAccount();
    Account getPrimaryAccount(long accountId, String username);
    Account getSavingAccount(long accountId);
}
