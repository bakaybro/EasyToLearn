package com.example.easytolearn.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
}
