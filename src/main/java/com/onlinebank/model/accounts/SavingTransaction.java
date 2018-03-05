package com.onlinebank.model.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class SavingTransaction extends Transaction {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private SavingAccount account;

}
