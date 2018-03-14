package com.onlinebank.model.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class SavingTransaction extends Transaction {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private SavingAccount account;


    public SavingTransaction() {
    }

    public SavingTransaction(Timestamp date, String description, String type, String status, double amount, BigDecimal availableBalance, SavingAccount account) {
        super(date, description, type, status, amount, availableBalance);
        this.account = account;
    }
}
