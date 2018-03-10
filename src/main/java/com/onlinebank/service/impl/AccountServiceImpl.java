package com.onlinebank.service.impl;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.Currency;
import com.onlinebank.model.accounts.PrimaryAccount;
import com.onlinebank.model.accounts.SavingAccount;
import com.onlinebank.repo.PrimaryAccountRepo;
import com.onlinebank.repo.SavingAccountRepo;
import com.onlinebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNum = 46454765;

    @Autowired
    private PrimaryAccountRepo primaryAccountRepo;
    @Autowired
    private SavingAccountRepo savingAccountRepo;

    @Override
    public PrimaryAccount createPrimaryAccount(Currency currency) {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(++nextAccountNum);
        primaryAccount.setCurrency(currency);

        primaryAccountRepo.save(primaryAccount);
        return primaryAccount;
    }

    @Override
    public SavingAccount createSavingAccount() {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setAccountBalance(new BigDecimal(0.0));
        savingAccount.setAccountNumber(++nextAccountNum);

        savingAccountRepo.save(savingAccount);
        return savingAccount;
    }

    @Override
    public Account getPrimaryAccount(long accountId, String username) {
        return primaryAccountRepo.findByIdAndUserUsername(accountId, username);
    }

    @Override
    public Account getSavingAccount(long accountId, String username) {
        return savingAccountRepo.findByIdAndUserUsername(accountId, username);
    }

}
