package com.example.easytolearn.service.book;

import com.example.easytolearn.converter.book.BookImageConverter;
import com.example.easytolearn.entity.Book;
import com.example.easytolearn.entity.BookImage;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.bookAndDelivery.BookImageModel;
import com.example.easytolearn.repository.book.BookImageRepository;
import com.example.easytolearn.service.UserService;
import com.example.easytolearn.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookImageServiceImpl implements BookImageService {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    private final BookImageRepository bookImageRepository;
    private final BookImageConverter bookImageConverter;

    @Override
    public BookImage save(BookImage courseImage) {
        return bookImageRepository.save(courseImage);
    }

    @Override
    public BookImageModel createBookImage(MultipartFile multipartFile, Long bookId) {
        Book book = getDataBookByBookIdWithCheckAccess(bookId);

        BookImage bookImage = bookImageRepository.findByBook_Id(bookId).orElse(null);

        if (bookImage != null)
            throw new ApiFailException("Изображение книги готово");

        String savedImageUrl = ImageUtil.saveImageInCloudinary(multipartFile);
        bookImage = new BookImage();
        bookImage.setBookImageUrl(savedImageUrl);
        bookImage.setBook(book);
        save(bookImage);

        return bookImageConverter.convertFromEntity(bookImage);
    }

    @Override
    public BookImage getById(Long id) {
        return bookImageRepository.findById(id).orElse(null);
    }

    @Override
    public BookImageModel getBookImageModelById(Long id) {
        return bookImageConverter.convertFromEntity(getById(id));
    }

    @Override
    public BookImageModel getBookImageModelByBookId(Long bookId) {
        BookImage bookImage = bookImageRepository.findByBook_Id(bookId).orElse(null);
        return bookImageConverter.convertFromEntity(bookImage);
    }

    @Override
    public List<BookImage> getAll() {
        return bookImageRepository.findAll();
    }

    @Override
    public List<BookImageModel> getAllUserImageModel() {
        return getAll()
                .stream()
                .map(bookImageConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BookImageModel updateImage(MultipartFile multipartFile, Long id) {
        BookImage updateBookImage = getDataBookImageByIdWithCheckAccess(id);
        String updateImageUrl = ImageUtil.saveImageInCloudinary(multipartFile);
        updateBookImage.setBookImageUrl(updateImageUrl);
        bookImageRepository.save(updateBookImage);
        return bookImageConverter.convertFromEntity(updateBookImage);
    }

    @Override
    public BookImageModel deleteImage(Long id) {
        BookImage deleteBookImage = getDataBookImageByIdWithCheckAccess(id);
        bookImageRepository.delete(deleteBookImage);
        return bookImageConverter.convertFromEntity(deleteBookImage);
    }

    private Book getDataBookByBookIdWithCheckAccess(Long bookId) {
        if (bookId == null)
            throw new ApiFailException("Не указан ID книги");

        Book book = bookService.getById(bookId);

        if (book == null)
            throw new ApiFailException("Книги под ID " + bookId + " не найден");

        checkAccess(book.getUser().getId());
        return book;
    }

    private BookImage getDataBookImageByIdWithCheckAccess(Long id) {
        if (id == null)
            throw new ApiFailException("Не указан ID изображения книги");

        BookImage dataBookImage = getById(id);

        if (dataBookImage == null)
            throw new ApiFailException("Изображение книги под ID " + id + " не найдено");

        Long authorBookId = dataBookImage.getBook().getUser().getId();
        checkAccess(authorBookId);
        return dataBookImage;
    }

    private void checkAccess(Long authorBookId) {
        Long currentUserId = userService.getCurrentUser().getId();

        if (!currentUserId.equals(authorBookId))
            throw new ApiFailException("Доступ ограничен");
    }
}
