package com.onlinebank.service;

import com.onlinebank.model.Currency;
import com.onlinebank.model.PrimaryAccount;
import com.onlinebank.model.SavingAccount;

public interface AccountService {
    PrimaryAccount createPrimaryAccount(Currency currency);
    SavingAccount createSavingAccount();
}
