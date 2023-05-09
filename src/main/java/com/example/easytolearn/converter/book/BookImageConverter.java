package com.example.easytolearn.converter.book;

import com.example.easytolearn.converter.BaseConverter;
import com.example.easytolearn.entity.Book;
import com.example.easytolearn.entity.BookImage;
import com.example.easytolearn.model.bookAndDelivery.BookImageModel;
import org.springframework.stereotype.Component;

@Component
public class BookImageConverter extends BaseConverter<BookImageModel, BookImage> {
    public BookImageConverter() {
        super(BookImageConverter::convertToEntity, BookImageConverter::convertToModel);
    }

    private static BookImageModel convertToModel(BookImage entityToConvert) {
        if (entityToConvert == null) return null;
        return BookImageModel.builder()
                .id(entityToConvert.getId())
                .bookImageUrl(entityToConvert.getBookImageUrl())
                .bookId(entityToConvert.getBook().getId())
                .build();
    }

    private static BookImage convertToEntity(BookImageModel modelToConvert) {
        if (modelToConvert == null) return null;

        BookImage bookImage = new BookImage();
        bookImage.setId(modelToConvert.getId());
        bookImage.setBookImageUrl(modelToConvert.getBookImageUrl());

        if (modelToConvert.getBookId() != null) {
            Book book = new Book();
            book.setId(modelToConvert.getBookId());
            bookImage.setBook(book);
        }
        return bookImage;
    }
}
