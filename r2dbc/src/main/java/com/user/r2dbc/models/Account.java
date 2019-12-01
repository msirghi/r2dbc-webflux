package com.user.r2dbc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;
    private String iban;
    private BigDecimal balance;

    public Account(Long id, String iban, Double balance) {
        this.id = id;
        this.iban = iban;
        this.balance = new BigDecimal(balance);
    }
}
