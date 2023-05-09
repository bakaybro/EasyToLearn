package com.example.easytolearn.repository.book;

import com.example.easytolearn.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookImageRepository extends JpaRepository<BookImage, Long> {
    Optional<BookImage> findByBook_Id(Long bookId);
}
