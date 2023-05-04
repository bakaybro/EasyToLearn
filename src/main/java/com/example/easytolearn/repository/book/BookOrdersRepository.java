package com.example.easytolearn.repository.book;

import com.example.easytolearn.entity.BookOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookOrdersRepository extends JpaRepository<BookOrders, Long> {
    List<BookOrders> findBookOrdersByOrderId(Long id);
}
