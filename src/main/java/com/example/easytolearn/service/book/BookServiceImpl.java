package com.example.easytolearn.service.book;

import com.example.easytolearn.converter.book.BookConverter;
import com.example.easytolearn.entity.Book;
import com.example.easytolearn.entity.Category;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.bookAndDelivery.BookModel;
import com.example.easytolearn.model.bookAndDelivery.CreateBookModel;
import com.example.easytolearn.repository.CategoryRepository;
import com.example.easytolearn.repository.book.BookRepository;
import com.example.easytolearn.service.CategoryService;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    private final BookConverter bookConverter;
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public BookModel createBook(CreateBookModel createBookModel) {
        Book book = new Book();
        book.setAuthor(createBookModel.getAuthor());
        book.setTitle(createBookModel.getTitle());
        book.setPublisher(createBookModel.getPublisher());
        book.setPrice(createBookModel.getPrice());
        book.setYear(createBookModel.getYear());
        book.setDescription(createBookModel.getDescription());

        Category category = categoryService.getById(createBookModel.getCategoryId());
        book.setCategory(category);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userService.getByUsername(username);
        createBookModel.setUserId(currentUser.getId());
        book.setUser(currentUser);

        save(book);

        return bookConverter.convertFromEntity(book);
    }

    @Override
    public List<BookModel> getAllBookModel() {
        return getAll()
                .stream()
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookModel> getAllByCategoryId(Long id) {
        return bookRepository.findAllByCategory_Id(id)
                .stream()
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookModel> getAllByCategoryName(String categoryName) {
        return bookRepository.findAllByCategoryName(categoryName.toLowerCase(Locale.ROOT))
                .stream()
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public BookModel updateBook(BookModel bookModel) {
        Long bookId = bookModel.getId();
        Book dataBook = getDataBookByIdWithCheckAccess(bookId);

        setVariablesForUpdateBook(dataBook, bookModel);
        bookRepository.save(dataBook);
        return bookConverter.convertFromEntity(dataBook);
    }

    @Override
    public BookModel deleteBookById(Long id) {
        Book dataBook = getDataBookByIdWithCheckAccess(id);
        bookRepository.delete(dataBook);
        return bookConverter.convertFromEntity(dataBook);
    }

    private Book getDataBookByIdWithCheckAccess(Long id) {
        if (id == null)
            throw new ApiFailException("Не указан ID книги");

        Book dataBook = getById(id);

        if (dataBook == null)
            throw new ApiFailException("Книга под ID " + id + " не найден");

        Long currentUserId = userService.getCurrentUser().getId();
        Long authorBookId = dataBook.getUser().getId();

        if (!currentUserId.equals(authorBookId))
            throw new ApiFailException("Доступ ограничен");

        return dataBook;
    }

    private void setVariablesForUpdateBook(Book book, BookModel bookModel) {
        if (bookModel.getAuthor() != null)
            book.setAuthor(bookModel.getAuthor());
        if (bookModel.getTitle() != null)
            book.setTitle(bookModel.getTitle());
        if (bookModel.getPublisher() != null)
            book.setPublisher(bookModel.getPublisher());
        if (bookModel.getYear() != null)
            book.setYear(bookModel.getYear());
        if (bookModel.getDescription() != null)
            book.setDescription(bookModel.getDescription());
        if (bookModel.getPrice() != null)
            book.setPrice(bookModel.getPrice());

        Category category = categoryService.getById(bookModel.getCategoryId());
        book.setCategory(category);
        if (bookModel.getCategoryId() != null)
            book.setCategory(category);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userService.getByUsername(username);
        book.setUser(currentUser);
    }
}
