package com.onlinebank.model.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "SavingAccount")
@Getter
@Setter
public class SavingAccount extends Account {

    //by default currency is RUR

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private final Currency currency = Currency.EUR;

}
