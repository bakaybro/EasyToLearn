package com.example.easytolearn.model.bookAndDelivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryModel {
    private Long id;
    private Long orderId;
    private Long userId;
    private String address;
    private String phoneNumber;
    private Double deliveryCost;
}
