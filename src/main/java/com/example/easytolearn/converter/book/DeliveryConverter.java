package com.example.easytolearn.converter.book;

import com.example.easytolearn.converter.BaseConverter;
import com.example.easytolearn.entity.*;
import com.example.easytolearn.model.bookAndDelivery.BookModel;
import com.example.easytolearn.model.bookAndDelivery.DeliveryModel;
import org.springframework.stereotype.Component;

@Component
public class DeliveryConverter extends BaseConverter<DeliveryModel, Delivery> {
    public DeliveryConverter() {
        super(DeliveryConverter::convertToEntity, DeliveryConverter::convertToModel);
    }

    private static DeliveryModel convertToModel(Delivery entityToConvert) {
        if (entityToConvert == null) return null;

        return DeliveryModel.builder()
                .id(entityToConvert.getId())
                .orderId(entityToConvert.getOrder().getId())
                .userId(entityToConvert.getUser().getId())
                .address(entityToConvert.getAddress())
                .phoneNumber(entityToConvert.getPhoneNumber())
                .deliveryCost(entityToConvert.getDeliveryCost())
                .build();
    }

    private static Delivery convertToEntity(DeliveryModel modelToConvert) {
        if (modelToConvert == null) return null;

        Delivery delivery = new Delivery();
        delivery.setId(modelToConvert.getId());
        delivery.setAddress(modelToConvert.getAddress());
        delivery.setPhoneNumber(modelToConvert.getPhoneNumber());
        delivery.setDeliveryCost(modelToConvert.getDeliveryCost());

        if (modelToConvert.getOrderId() != null) {
            Order order = new Order();
            order.setId(modelToConvert.getOrderId());
            delivery.setOrder(order);
        }

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            delivery.setUser(user);
        }

        return delivery;
    }

}
