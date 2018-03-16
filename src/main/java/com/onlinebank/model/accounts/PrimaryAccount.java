package com.onlinebank.model.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "PrimaryAccount")
@Getter
@Setter
public class PrimaryAccount extends Account {

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

}
