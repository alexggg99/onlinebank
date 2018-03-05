package com.onlinebank.model.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebank.model.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@MappedSuperclass
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
