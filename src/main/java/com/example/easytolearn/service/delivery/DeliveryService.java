package com.example.easytolearn.service.delivery;

import com.example.easytolearn.entity.Delivery;
import com.example.easytolearn.model.bookAndDelivery.DeliveryModel;

import java.util.List;

public interface DeliveryService {
    Delivery createDelivery(DeliveryModel deliveryModel);

    List<Delivery> getAll();

    Delivery getById(Long id);

    DeliveryModel updateDelivery(DeliveryModel deliveryModel);

    DeliveryModel deleteById(Long id);
}
