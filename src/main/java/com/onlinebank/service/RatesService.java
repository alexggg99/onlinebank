package com.onlinebank.service;

import com.onlinebank.model.Rates;
import com.onlinebank.model.accounts.Account;

import java.math.BigDecimal;
import java.util.Date;

public interface RatesService {
    Rates getRates(Date date);
    BigDecimal exchangeAmmount(Account accountFrom, Account accountTo, BigDecimal amount);
}
