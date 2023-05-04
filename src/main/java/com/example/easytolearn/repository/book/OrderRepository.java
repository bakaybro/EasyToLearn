package com.example.easytolearn.repository.book;

import com.example.easytolearn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
