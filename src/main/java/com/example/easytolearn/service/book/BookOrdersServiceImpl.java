package com.example.easytolearn.service.book;

import com.example.easytolearn.converter.book.BookOrdersConverter;
import com.example.easytolearn.entity.*;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.bookAndDelivery.BookModel;
import com.example.easytolearn.model.bookAndDelivery.BookOrdersModel;
import com.example.easytolearn.repository.book.BookOrdersRepository;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookOrdersServiceImpl implements BookOrdersService {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    private final BookOrdersConverter bookOrdersConverter;
    private final BookOrdersRepository bookOrdersRepository;

    @Override
    public BookOrders createBookOrders(BookOrdersModel bookOrdersModel) {
        User user = userService.getById(bookOrdersModel.getUserId());
        Book book = bookService.getById(bookOrdersModel.getBookId());
        Order order = orderService.getById(bookOrdersModel.getOrderId());
        BookOrders bookOrders = bookOrdersConverter.convertFromModel(bookOrdersModel);
        bookOrders.setUser(user);
        bookOrders.setBook(book);
        bookOrders.setOrder(order);
        return bookOrdersRepository.save(bookOrders);
    }

    @Override
    public List<BookOrders> getAll() {
        return bookOrdersRepository.findAll();
    }

    @Override
    public BookOrders getById(Long id) {
        return bookOrdersRepository.findById(id).orElse(null);
    }

    @Override
    public BookOrdersModel updateBookOrders(BookOrdersModel bookOrdersModel) {
        Long bookOrdersId = bookOrdersModel.getId();
        BookOrders bookOrdersForUpdate = getDataBookOrdersByIdWithCheckAccess(bookOrdersId);

        setVariablesForUpdateBookOrders(bookOrdersForUpdate, bookOrdersModel);
        bookOrdersRepository.save(bookOrdersForUpdate);
        return bookOrdersConverter.convertFromEntity(bookOrdersForUpdate);
    }

    @Override
    public BookOrdersModel deleteById(Long id) {
        BookOrders bookOrdersForDelete = getDataBookOrdersByIdWithCheckAccess(id);
        bookOrdersRepository.delete(bookOrdersForDelete);
        return bookOrdersConverter.convertFromEntity(bookOrdersForDelete);
    }

    @Override
    public List<Book> findBookOrdersByOrderId(Long id) {
        return bookOrdersRepository.findBookOrdersByOrderId(id).stream().map(x -> x.getBook()).collect(Collectors.toList());
    }

    private BookOrders getDataBookOrdersByIdWithCheckAccess(Long id) {
        if (id == null)
            throw new ApiFailException("Не указан ID книги");

        BookOrders dataBookOrders = getById(id);

        if (dataBookOrders == null)
            throw new ApiFailException("BookOrders под ID " + id + " не найден");

        Long currentUserId = userService.getCurrentUser().getId();
        Long authorBookOrdersId = dataBookOrders.getUser().getId();

        if (!currentUserId.equals(authorBookOrdersId))
            throw new ApiFailException("Доступ ограничен");

        return dataBookOrders;
    }

    private void setVariablesForUpdateBookOrders(BookOrders bookOrders, BookOrdersModel bookOrdersModel) {
        Book book = bookService.getById(bookOrdersModel.getBookId());
        bookOrders.setBook(book);

        Order order = orderService.getById(bookOrdersModel.getOrderId());
        bookOrders.setOrder(order);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userService.getByUsername(username);
        bookOrders.setUser(currentUser);
    }
}
