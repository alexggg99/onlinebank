package com.onlinebank.service.impl;

import com.onlinebank.exceptions.GeneralException;
import com.onlinebank.exceptions.NotEnoughAccountBalance;
import com.onlinebank.model.accounts.*;
import com.onlinebank.repo.*;
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

@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNum = 46454765;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private UserService userService;

    @Override
    public List<Account> getAllAccounts(String username) {
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(accountRepo.findByUserUsernameOrderById(username));
        return accounts;
    }

    @Override
    public PrimaryAccount createPrimaryAccount(Currency currency, String username) {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(++nextAccountNum);
        primaryAccount.setCurrency(currency);
        primaryAccount.setUser(userService.findByUsername(username));
        accountRepo.save(primaryAccount);
        return primaryAccount;
    }

    @Override
    public SavingAccount createSavingAccount(String username) {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setAccountBalance(new BigDecimal(0.0));
        savingAccount.setAccountNumber(++nextAccountNum);
        savingAccount.setUser(userService.findByUsername(username));
        accountRepo.save(savingAccount);
        return savingAccount;
    }

    @Override
    public Account getPrimaryAccount(long accountId, String username) {
        return accountRepo.findByIdAndUserUsername(accountId, username);
    }

    @Override
    public Account getSavingAccount(long accountId, String username) {
        return accountRepo.findByIdAndUserUsername(accountId, username);
    }

    @Override
    public void saveAccount(Account account) {
        if (account instanceof PrimaryAccount) {
            accountRepo.save((PrimaryAccount) account);
        } else {
            accountRepo.save((SavingAccount) account);
        }
    }

    @Override
    @Transactional
    public void manageAccount(String action, String accountId, BigDecimal amount, String username) {
        Account account;
        Calendar calendar = Calendar.getInstance();
        if(accountId.startsWith("P")) {
            account = getPrimaryAccount(Long.valueOf(accountId.substring(1)), username);
        } else {
            account = getSavingAccount(Long.valueOf(accountId.substring(1)), username);
        }
        if(account == null) {
            throw new GeneralException();
        }
        if ("deposit".equals(action)) {
            account.setAccountBalance(account.getAccountBalance().add(amount));
        } else {
            account.setAccountBalance(account.getAccountBalance().subtract(amount));
        }
        accountRepo.save(account);

        Transaction transaction = new Transaction(new Timestamp(calendar.getTime().getTime()),"",
                                                                        action,
                                                                        "ok", amount.doubleValue(), account.getAccountBalance(), account);
        transactionRepo.save(transaction);

    }

    @Override
    @Transactional
    public void transferBetweenAccounts(String accountIdFrom, String accountIdTo, BigDecimal amount, String username) {
        Account accountFrom;
        Account accountTo;
        Calendar calendar = Calendar.getInstance();
        if(accountIdFrom.startsWith("P")) {
            accountFrom = getPrimaryAccount(Long.valueOf(accountIdFrom.substring(1)), username);
        } else {
            accountFrom = getSavingAccount(Long.valueOf(accountIdFrom.substring(1)), username);
        }
        if(accountIdTo.startsWith("P")) {
            accountTo = getPrimaryAccount(Long.valueOf(accountIdTo.substring(1)), username);
        } else {
            accountTo = getSavingAccount(Long.valueOf(accountIdTo.substring(1)), username);
        }
        if (accountFrom == null || accountTo == null || accountFrom.getId() == accountTo.getId()) {
            throw new GeneralException();
        }
        if (accountFrom.getAccountBalance().compareTo(amount) >= 0) {
            accountFrom.setAccountBalance(accountFrom.getAccountBalance().subtract(amount));
            accountTo.setAccountBalance(accountTo.getAccountBalance().add(amount));
        } else {
            throw new NotEnoughAccountBalance();
        }

        Transaction transactionFrom = new Transaction(new Timestamp(calendar.getTime().getTime()),"",
                "transfer",
                "ok", amount.negate().doubleValue(), accountFrom.getAccountBalance(), accountFrom);
        Transaction transactionTo = new Transaction(new Timestamp(calendar.getTime().getTime()),"",
                "transfer",
                "ok", amount.doubleValue(), accountFrom.getAccountBalance(), accountTo);
        transactionRepo.save(transactionFrom);
        transactionRepo.save(transactionTo);
    }
}
