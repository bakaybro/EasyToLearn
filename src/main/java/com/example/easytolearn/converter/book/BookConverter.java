package com.example.easytolearn.converter.book;

import com.example.easytolearn.converter.BaseConverter;
import com.example.easytolearn.converter.CategoryConverter;
import com.example.easytolearn.entity.Book;
import com.example.easytolearn.entity.Category;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.CategoryModel;
import com.example.easytolearn.model.bookAndDelivery.BookModel;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends BaseConverter<BookModel, Book> {
    public BookConverter() {
        super(BookConverter::convertToEntity, BookConverter::convertToModel);
    }

    private static BookModel convertToModel(Book entityToConvert) {
        if (entityToConvert == null) return null;

        Long categoryId = null;
        if (entityToConvert.getCategory() != null)
            categoryId = entityToConvert.getCategory().getId();
        return BookModel.builder()
                .id(entityToConvert.getId())
                .title(entityToConvert.getTitle())
                .author(entityToConvert.getAuthor())
                .categoryId(categoryId)
                .description(entityToConvert.getDescription())
                .publisher(entityToConvert.getPublisher())
                .price(entityToConvert.getPrice())
                .year(entityToConvert.getYear())
                .userId(entityToConvert.getUser().getId())
                .build();
    }

    private static Book convertToEntity(BookModel modelToConvert) {
        if (modelToConvert == null) return null;

        Book book = new Book();
        book.setId(modelToConvert.getId());
        book.setTitle(modelToConvert.getTitle());
        book.setAuthor(modelToConvert.getAuthor());
        book.setDescription(modelToConvert.getDescription());
        book.setPublisher(modelToConvert.getPublisher());
        book.setPrice(modelToConvert.getPrice());
        book.setYear(modelToConvert.getYear());

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            book.setUser(user);
        }

        if (modelToConvert.getCategoryId() != null) {
            Category category = new Category();
            category.setId(modelToConvert.getCategoryId());
            book.setCategory(category);
        }

        return book;
    }
}
