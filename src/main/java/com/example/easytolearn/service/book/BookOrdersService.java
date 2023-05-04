package com.example.easytolearn.service.book;

import com.example.easytolearn.entity.Book;
import com.example.easytolearn.entity.BookOrders;
import com.example.easytolearn.model.bookAndDelivery.BookOrdersModel;

import java.util.List;

public interface BookOrdersService {
    BookOrders createBookOrders(BookOrdersModel bookOrdersModel);

    List<BookOrders> getAll();

    BookOrders getById(Long id);

    BookOrdersModel updateBookOrders(BookOrdersModel bookOrdersModel);

    BookOrdersModel deleteById(Long id);

    List<Book> findBookOrdersByOrderId(Long id);
}

