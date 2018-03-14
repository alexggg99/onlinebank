package com.onlinebank.service.impl;

import com.onlinebank.model.accounts.*;
import com.onlinebank.repo.PrimaryAccountRepo;
import com.onlinebank.repo.PrimaryTransactionRepo;
import com.onlinebank.repo.SavingAccountRepo;
import com.onlinebank.repo.SavingTransactionRepo;
import com.onlinebank.service.AccountService;
import com.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNum = 46454765;

    @Autowired
    private PrimaryAccountRepo primaryAccountRepo;
    @Autowired
    private SavingAccountRepo savingAccountRepo;
    @Autowired
    private PrimaryTransactionRepo primaryTransactionRepo;
    @Autowired
    private SavingTransactionRepo savingTransactionRepo;
    @Autowired
    private UserService userService;

    @Override
    public List<Account> getAllAccounts(String username) {
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(primaryAccountRepo.findByUser_Username(username));
        accounts.addAll(savingAccountRepo.findByUser_Username(username));
        return accounts;
    }

    @Override
    public PrimaryAccount createPrimaryAccount(Currency currency, String username) {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(++nextAccountNum);
        primaryAccount.setCurrency(currency);
        primaryAccount.setUser(userService.findByUsername(username));
        primaryAccountRepo.save(primaryAccount);
        return primaryAccount;
    }

    @Override
    public SavingAccount createSavingAccount(String username) {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setAccountBalance(new BigDecimal(0.0));
        savingAccount.setAccountNumber(++nextAccountNum);
        savingAccount.setUser(userService.findByUsername(username));
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

    @Override
    public void depositMoney(String accountId, BigDecimal amount, String username) {
        Account account;
        Calendar calendar = Calendar.getInstance();
        if(accountId.startsWith("P")) {
            account = getPrimaryAccount(Long.valueOf(accountId.substring(1)), username);
            account.setAccountBalance(account.getAccountBalance().add(amount));
            primaryAccountRepo.save((PrimaryAccount) account);

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Timestamp(calendar.getTime().getTime()),"Deposit account",
                                                                            "deposit",
                                                                            "ok", amount.doubleValue(), account.getAccountBalance(), (PrimaryAccount) account);
            primaryTransactionRepo.save(primaryTransaction);
        } else {
            account = getSavingAccount(Long.valueOf(accountId.substring(1)), username);
            account.setAccountBalance(account.getAccountBalance().add(amount));
            savingAccountRepo.save((SavingAccount) account);

            SavingTransaction savingTransaction = new SavingTransaction(new Timestamp(calendar.getTime().getTime()),"Deposit account",
                                                                        "deposit",
                                                                        "ok", amount.doubleValue(), account.getAccountBalance(), (SavingAccount) account);

            savingTransactionRepo.save(savingTransaction);
        }

    }

}
