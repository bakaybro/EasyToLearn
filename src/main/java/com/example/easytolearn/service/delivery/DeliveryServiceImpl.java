package com.example.easytolearn.service.delivery;

import com.example.easytolearn.converter.book.DeliveryConverter;
import com.example.easytolearn.entity.*;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.bookAndDelivery.DeliveryModel;
import com.example.easytolearn.repository.book.DeliveryRepository;
import com.example.easytolearn.service.UserService;
import com.example.easytolearn.service.book.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private final DeliveryRepository deliveryRepository;
    private final DeliveryConverter deliveryConverter;

    @Override
    public Delivery createDelivery(DeliveryModel deliveryModel) {
        if (deliveryRepository.findByOrderId(deliveryModel.getOrderId()).isPresent()) {
            throw new ApiFailException("Доставка уже оформлена на этот заказ");
        } else {
            User user = userService.getById(deliveryModel.getUserId());
            Order order = orderService.getById(deliveryModel.getOrderId());
            Delivery delivery = deliveryConverter.convertFromModel(deliveryModel);
            delivery.setUser(user);
            delivery.setOrder(order);
            delivery.setAddress(deliveryModel.getAddress());
            delivery.setDeliveryCost(deliveryModel.getDeliveryCost());
            delivery.setPhoneNumber(deliveryModel.getPhoneNumber());
            return deliveryRepository.save(delivery);
        }
    }

    @Override
    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery getById(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    @Override
    public DeliveryModel updateDelivery(DeliveryModel deliveryModel) {
        Long deliveryId = deliveryModel.getId();
        Delivery deliveryForUpdate = getDataDeliveryByIdWithCheckAccess(deliveryId);

        setVariablesForUpdateDelivery(deliveryForUpdate, deliveryModel);
        deliveryRepository.save(deliveryForUpdate);
        return deliveryConverter.convertFromEntity(deliveryForUpdate);
    }

    @Override
    public DeliveryModel deleteById(Long id) {
        Delivery deliveryForUpdate = getDataDeliveryByIdWithCheckAccess(id);
        deliveryRepository.delete(deliveryForUpdate);
        return deliveryConverter.convertFromEntity(deliveryForUpdate);
    }

    private Delivery getDataDeliveryByIdWithCheckAccess(Long id) {
        if (id == null)
            throw new ApiFailException("Не указан ID книги");

        Delivery dataDelivery = getById(id);

        if (dataDelivery == null)
            throw new ApiFailException("Delivery под ID " + id + " не найден");

        Long currentUserId = userService.getCurrentUser().getId();
        Long authorDeliveryOrdersId = dataDelivery.getUser().getId();

        if (!currentUserId.equals(authorDeliveryOrdersId))
            throw new ApiFailException("Доступ ограничен");

        return dataDelivery;
    }

    private void setVariablesForUpdateDelivery(Delivery delivery, DeliveryModel deliveryModel) {
        if (deliveryModel.getAddress() != null)
            delivery.setAddress(deliveryModel.getAddress());
        if (deliveryModel.getPhoneNumber() != null)
            delivery.setPhoneNumber(deliveryModel.getPhoneNumber());
        if (deliveryModel.getDeliveryCost() != null)
            delivery.setDeliveryCost(deliveryModel.getDeliveryCost());

        Order order = orderService.getById(deliveryModel.getOrderId());
        delivery.setOrder(order);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userService.getByUsername(username);
        delivery.setUser(currentUser);
    }
}
