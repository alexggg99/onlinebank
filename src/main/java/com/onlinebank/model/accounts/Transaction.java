package com.onlinebank.model.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;

    public Transaction() {
    }

    public Transaction(Timestamp date, String status, double amount) {
        this.date = date;
        this.status = status;
        this.amount = amount;
    }

    public Transaction(Timestamp date, String description, String type, String status, double amount, BigDecimal availableBalance) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
    }
}
