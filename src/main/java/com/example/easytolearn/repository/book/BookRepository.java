package com.example.easytolearn.repository.book;

import com.example.easytolearn.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategory_Id(Long id);

    @Query("FROM Book b JOIN b.category r WHERE r.categoryName LIKE :categoryName%")
    List<Book> findAllByCategoryName(@Param("categoryName") String categoryName);
}
