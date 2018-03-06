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
    private long id;
    private Timestamp date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;

}
