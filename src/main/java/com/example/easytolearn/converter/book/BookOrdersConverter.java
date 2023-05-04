package com.example.easytolearn.converter.book;

import com.example.easytolearn.converter.BaseConverter;
import com.example.easytolearn.entity.Book;
import com.example.easytolearn.entity.BookOrders;
import com.example.easytolearn.entity.Order;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.bookAndDelivery.BookOrdersModel;
import org.springframework.stereotype.Component;

@Component
public class BookOrdersConverter extends BaseConverter<BookOrdersModel, BookOrders> {
    public BookOrdersConverter() {
        super(BookOrdersConverter::convertToEntity, BookOrdersConverter::convertToModel);
    }

    private static BookOrdersModel convertToModel(BookOrders entityToConvert) {
        if (entityToConvert == null) return null;

        return BookOrdersModel.builder()
                .id(entityToConvert.getId())
                .userId(entityToConvert.getUser().getId())
                .orderId(entityToConvert.getOrder().getId())
                .bookId(entityToConvert.getBook().getId())
                .build();
    }

    private static BookOrders convertToEntity(BookOrdersModel modelToConvert) {
        if (modelToConvert == null) return null;

        BookOrders bookOrders = new BookOrders();
        bookOrders.setId(modelToConvert.getId());

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            bookOrders.setUser(user);
        }

        if (modelToConvert.getOrderId() != null) {
            Order order = new Order();
            order.setId(modelToConvert.getOrderId());
            bookOrders.setOrder(order);
        }

        if (modelToConvert.getBookId() != null) {
            Book book = new Book();
            book.setId(modelToConvert.getBookId());
            bookOrders.setBook(book);
        }

        return bookOrders;
    }
}
