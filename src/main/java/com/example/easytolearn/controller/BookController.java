package com.example.easytolearn.controller;

import com.example.easytolearn.model.bookAndDelivery.BookModel;
import com.example.easytolearn.model.bookAndDelivery.CreateBookModel;
import com.example.easytolearn.service.book.BookService;
import com.example.easytolearn.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")
    public ResponseMessage<BookModel> save(@RequestBody CreateBookModel createBookModel) {
        BookModel createdBook = bookService.createBook(createBookModel);
        if (createdBook != null) {
            return ResponseMessage.<BookModel>builder()
                    .value(createdBook)
                    .status("OK")
                    .details(null)
                    .build();
        } else {
            return ResponseMessage.<BookModel>builder()
                    .value(null)
                    .status("FAIL")
                    .details("Failed to create book.")
                    .build();
        }
    }

    @PutMapping("/update")
    public ResponseMessage<BookModel> update(@RequestBody BookModel bookModel) {
        return new ResponseMessage<BookModel>()
                .prepareSuccessMessage(bookService.updateBook(bookModel));
    }

    @GetMapping("/get-all")
    public ResponseMessage<List<BookModel>> getAll() {
        return new ResponseMessage<List<BookModel>>()
                .prepareSuccessMessage(bookService.getAllBookModel());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<BookModel> delete(@PathVariable Long id) {
        return new ResponseMessage<BookModel>()
                .prepareSuccessMessage(bookService.deleteBookById(id));
    }

    @GetMapping("/get-all/by-category-id/{categoryId}")
    public ResponseMessage<List<BookModel>> getAllByCategoryId(@PathVariable Long categoryId) {
        return new ResponseMessage<List<BookModel>>()
                .prepareSuccessMessage(bookService.getAllByCategoryId(categoryId));
    }

    @GetMapping("/get-all/by-category-name/{categoryName}")
    public ResponseMessage<List<BookModel>> getAllByCategoryName(@PathVariable String categoryName) {
        return new ResponseMessage<List<BookModel>>()
                .prepareSuccessMessage(bookService.getAllByCategoryName(categoryName));
    }
}
