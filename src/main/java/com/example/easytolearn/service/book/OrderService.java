package com.example.easytolearn.service.book;

import com.example.easytolearn.entity.Order;
import com.example.easytolearn.model.order.ItemsForBuyModel;
import com.example.easytolearn.model.order.OrderModel;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderModel orderModel);
    List<Order> getAll();
    Order getById(Long id);
    Order updateOrder(Order order);
    Order deleteById(Long id);
    Order buyItems(ItemsForBuyModel itemsForBuyModel);
}
