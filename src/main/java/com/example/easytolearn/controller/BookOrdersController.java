package com.example.easytolearn.controller;

import com.example.easytolearn.entity.Book;
import com.example.easytolearn.entity.BookOrders;
import com.example.easytolearn.model.bookAndDelivery.BookOrdersModel;
import com.example.easytolearn.service.book.BookOrdersService;
import com.example.easytolearn.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-orders")
@RequiredArgsConstructor
public class BookOrdersController {
    private final BookOrdersService bookOrdersService;

    @GetMapping("/get-all")
    public ResponseMessage<List<BookOrders>> getAll() {
        return new ResponseMessage<List<BookOrders>>()
                .prepareSuccessMessage(bookOrdersService.getAll());
    }

    @GetMapping("get-by-id/{id}")
    public ResponseMessage<BookOrders> getById(@PathVariable Long id) {
        return new ResponseMessage<BookOrders>()
                .prepareSuccessMessage(bookOrdersService.getById(id));
    }

    @GetMapping("/order/{id}")
    public ResponseMessage<List<Book>> findBookOrdersByOrderId(@PathVariable Long id) {
        return new ResponseMessage<List<Book>>()
                .prepareSuccessMessage(bookOrdersService.findBookOrdersByOrderId(id));
    }

    @PostMapping("/create")
    public ResponseMessage<BookOrders> createBookOrders(@RequestBody BookOrdersModel bookOrdersModel){
        return new ResponseMessage<BookOrders>()
                .prepareSuccessMessage(bookOrdersService.createBookOrders(bookOrdersModel));
    }

    @PutMapping("/update")
    public ResponseMessage<BookOrdersModel> update(@RequestBody BookOrdersModel bookOrdersModel){
        return new ResponseMessage<BookOrdersModel>()
                .prepareSuccessMessage(bookOrdersService.updateBookOrders(bookOrdersModel));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseMessage<BookOrdersModel> deleteById(@PathVariable Long id){
        return new ResponseMessage<BookOrdersModel>()
                .prepareSuccessMessage(bookOrdersService.deleteById(id));
    }
}
