package com.example.easytolearn.controller;

import com.example.easytolearn.model.bookAndDelivery.BookImageModel;
import com.example.easytolearn.service.book.BookImageService;
import com.example.easytolearn.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/book-image")
@RequiredArgsConstructor
public class BookImageController {
    private final BookImageService bookImageService;

    @PostMapping("/create/{bookId}")
    public ResponseMessage<BookImageModel> save(@RequestParam(name = "file") MultipartFile file,
                                                @PathVariable Long bookId) {
        return new ResponseMessage<BookImageModel>()
                .prepareSuccessMessage(bookImageService.createBookImage(file, bookId));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<BookImageModel> getById(@PathVariable Long id) {
        return new ResponseMessage<BookImageModel>()
                .prepareSuccessMessage(bookImageService.getBookImageModelById(id));
    }

    @GetMapping("/get-by-book-id/{bookId}")
    public ResponseMessage<BookImageModel> getByBookId(@PathVariable Long bookId) {
        return new ResponseMessage<BookImageModel>()
                .prepareSuccessMessage(bookImageService.getBookImageModelByBookId(bookId));
    }

    @PutMapping("/update/{id}")
    public ResponseMessage<BookImageModel> update(@RequestParam(name = "file") MultipartFile multipartFile,
                                                    @PathVariable Long id) {
        return new ResponseMessage<BookImageModel>()
                .prepareSuccessMessage(bookImageService.updateImage(multipartFile, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<BookImageModel> delete(@PathVariable Long id) {
        return new ResponseMessage<BookImageModel>()
                .prepareSuccessMessage(bookImageService.deleteImage(id));
    }
}