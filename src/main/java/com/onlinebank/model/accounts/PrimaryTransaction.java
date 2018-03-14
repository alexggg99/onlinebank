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
public class PrimaryTransaction extends Transaction {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private PrimaryAccount account;

    public PrimaryTransaction() {
    }

    public PrimaryTransaction(Timestamp date, String status, double amount, PrimaryAccount account) {
        super(date, status, amount);
        this.account = account;
    }

    public PrimaryTransaction(Timestamp date, String description, String type, String status, double amount, BigDecimal availableBalance, PrimaryAccount account) {
        super(date, description, type, status, amount, availableBalance);
        this.account = account;
    }
}
