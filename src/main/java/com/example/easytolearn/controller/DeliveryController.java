package com.example.easytolearn.controller;

import com.example.easytolearn.entity.Delivery;
import com.example.easytolearn.model.bookAndDelivery.DeliveryModel;
import com.example.easytolearn.service.delivery.DeliveryService;
import com.example.easytolearn.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/create")
    public ResponseMessage<Delivery> createDelivery(@RequestBody DeliveryModel deliveryModel) {
        return new ResponseMessage<Delivery>()
                .prepareSuccessMessage(deliveryService.createDelivery(deliveryModel));
    }

    @GetMapping("/get-all")
    public ResponseMessage<List<Delivery>> getAll() {
        return new ResponseMessage<List<Delivery>>()
                .prepareSuccessMessage(deliveryService.getAll());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<Delivery> getById(@PathVariable Long id) {
        return new ResponseMessage<Delivery>()
                .prepareSuccessMessage(deliveryService.getById(id));
    }

    @PutMapping("/update")
    public ResponseMessage<DeliveryModel> update(@RequestBody DeliveryModel deliveryModel) {
        return new ResponseMessage<DeliveryModel>()
                .prepareSuccessMessage(deliveryService.updateDelivery(deliveryModel));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseMessage<DeliveryModel> deleteById(@PathVariable Long id) {
        return new ResponseMessage<DeliveryModel>()
                .prepareSuccessMessage(deliveryService.deleteById(id));
    }
}
