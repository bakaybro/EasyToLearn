package com.example.easytolearn.service.book;

import com.example.easytolearn.converter.book.BookOrdersConverter;
import com.example.easytolearn.converter.book.OrderConverter;
import com.example.easytolearn.entity.Book;
import com.example.easytolearn.entity.BookOrders;
import com.example.easytolearn.entity.Order;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.order.ItemsForBuyModel;
import com.example.easytolearn.model.order.OrderModel;
import com.example.easytolearn.repository.UserRepository;
import com.example.easytolearn.repository.book.OrderRepository;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserService userService;

    @Autowired
    private BookOrdersService bookOrdersService;

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final BookOrdersConverter bookOrdersConverter;

    @Override
    public Order createOrder(OrderModel orderModel) {
        User user = userService.getById(orderModel.getUserId());
        Order order = orderConverter.convertFromModel(orderModel);
        order.setUser(user);
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));
    }

    @Override
    public Order updateOrder(Order order) {
        Order orderForUpdate = getById(order.getId());

        if (order.getUser() != null) orderForUpdate.setUser(order.getUser());
        if (order.getTotalPrice() != null) orderForUpdate.setTotalPrice(order.getTotalPrice());

        return orderRepository.save(orderForUpdate);
    }

    @Override
    public Order deleteById(Long id) {
        Order orderForDelete = getById(id);
        if (orderForDelete != null) orderRepository.delete(orderForDelete);

        return orderForDelete;
    }

    @Override
    public Order buyItems(ItemsForBuyModel itemsForBuyModel) {
        Order order = new Order();
        order.setTotalPrice(BigDecimal.valueOf(0.00));

        User user = userService.getById(itemsForBuyModel.getUserId());//получить по userId
        order.setUser(user);

        for(Book book : itemsForBuyModel.getBookList()) {
            order.setTotalPrice(order.getTotalPrice().add(book.getPrice()));
        }
        orderRepository.save(order);

        for(Book book : itemsForBuyModel.getBookList()) {
            BookOrders bookOrders = new BookOrders();

            bookOrders.setBook(book);
            bookOrders.setUser(user);
            bookOrders.setOrder(order);

            bookOrdersService.createBookOrders(bookOrdersConverter.convertFromEntity(bookOrders));
        }

//        считаем сумму for each product in products
//        orderService.save(order) -- сохранили order и теперь знаем его ID
//        опять проходимся по продуктам и сохраняем в таблицу user_products, userId, product.getId, order.getId

        return order;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
