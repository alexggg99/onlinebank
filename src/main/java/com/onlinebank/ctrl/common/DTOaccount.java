package com.onlinebank.ctrl.common;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.Currency;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DTOaccount extends Account {
    private String type;
    private Currency currency;

    public DTOaccount(long id, String type, BigDecimal accountBalance, int accountNumber, Currency cur) {
        this.setId(id);
        this.setAccountBalance(accountBalance);
        this.setAccountNumber(accountNumber);
        this.type = type;
        this.currency = cur;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

}