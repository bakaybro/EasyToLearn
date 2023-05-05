package com.example.easytolearn.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "delivery_cost")
    private Double deliveryCost;
}
