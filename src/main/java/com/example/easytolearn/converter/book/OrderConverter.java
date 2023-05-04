package com.example.easytolearn.converter.book;

import com.example.easytolearn.converter.BaseConverter;
import com.example.easytolearn.entity.Order;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.order.OrderModel;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends BaseConverter<OrderModel, Order> {
    public OrderConverter() {
        super(OrderConverter::convertToEntity, OrderConverter::convertToModel);
    }

    private static OrderModel convertToModel(Order entityToConvert) {
        if (entityToConvert == null) return null;

        return OrderModel.builder()
//                .id(entityToConvert.getId())
                .userId(entityToConvert.getUser().getId())
                .totalPrice(entityToConvert.getTotalPrice())
                .build();
    }

    private static Order convertToEntity(OrderModel modelToConvert) {
        if (modelToConvert == null) return null;

        Order order = new Order();
//        order.setId(modelToConvert.getId());
        order.setTotalPrice(modelToConvert.getTotalPrice());

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            order.setUser(user);
        }

        return order;
    }
}
