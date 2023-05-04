package com.example.easytolearn.controller;

import com.example.easytolearn.entity.Order;
import com.example.easytolearn.model.order.ItemsForBuyModel;
import com.example.easytolearn.model.order.OrderModel;
import com.example.easytolearn.service.book.OrderService;
import com.example.easytolearn.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseMessage<Order> save(@RequestBody OrderModel orderModel) {
        return new ResponseMessage<Order>()
                .prepareSuccessMessage(orderService.createOrder(orderModel));
    }

    @GetMapping("/get-all")
    public ResponseMessage<List<Order>> getAll() {
        return new ResponseMessage<List<Order>>()
                .prepareSuccessMessage(orderService.getAll());
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<Order> getById(@PathVariable Long id) {
        return new ResponseMessage<Order>()
                .prepareSuccessMessage(orderService.getById(id));
    }

    @PutMapping("/update")
    public ResponseMessage<Order> update(@RequestBody Order order) {
        return new ResponseMessage<Order>()
                .prepareSuccessMessage(orderService.updateOrder(order));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseMessage<Order> deleteById(@PathVariable Long id) {
        return new ResponseMessage<Order>()
                .prepareSuccessMessage(orderService.deleteById(id));
    }

    @PostMapping("/buy")
    public ResponseMessage<Order> buyItems(@RequestBody ItemsForBuyModel itemsForBuyModel) {
        return new ResponseMessage<Order>()
                .prepareSuccessMessage(orderService.buyItems(itemsForBuyModel));
    }
}
