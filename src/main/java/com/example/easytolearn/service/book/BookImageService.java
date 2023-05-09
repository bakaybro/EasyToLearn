package com.example.easytolearn.service.book;

import com.example.easytolearn.entity.BookImage;
import com.example.easytolearn.model.bookAndDelivery.BookImageModel;
import com.example.easytolearn.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookImageService extends BaseService<BookImage> {
    BookImageModel createBookImage(MultipartFile multipartFile, Long bookId);

    BookImageModel updateImage(MultipartFile multipartFile, Long id);

    BookImageModel deleteImage(Long id);

    BookImageModel getBookImageModelById(Long id);

    BookImageModel getBookImageModelByBookId(Long bookId);

    List<BookImageModel> getAllUserImageModel();
}