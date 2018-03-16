package com.onlinebank.model.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "SavingAccount")
@Getter
@Setter
public class SavingAccount extends Account {
}
