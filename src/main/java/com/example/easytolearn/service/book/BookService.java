package com.example.easytolearn.service.book;

import com.example.easytolearn.entity.Book;
import com.example.easytolearn.model.bookAndDelivery.BookModel;
import com.example.easytolearn.model.bookAndDelivery.CreateBookModel;
import com.example.easytolearn.service.BaseService;

import java.util.List;

public interface BookService extends BaseService<Book> {
    BookModel createBook(CreateBookModel createBookModel);

    List<BookModel> getAllBookModel();

    List<BookModel> getAllByCategoryId(Long id);

    List<BookModel> getAllByCategoryName(String categoryName);

    Book getById(Long id);

    BookModel updateBook(BookModel bookModel);

    BookModel deleteBookById(Long id);
}
