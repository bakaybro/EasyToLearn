package com.example.easytolearn.model.balance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserBalanceModel {
    private Long id;
    private Long userId;
    private BigDecimal userBalance;
}