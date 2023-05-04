package com.example.easytolearn.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
}
