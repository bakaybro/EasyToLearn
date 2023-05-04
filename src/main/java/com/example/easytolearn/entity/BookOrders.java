package com.example.easytolearn.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookOrders extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
