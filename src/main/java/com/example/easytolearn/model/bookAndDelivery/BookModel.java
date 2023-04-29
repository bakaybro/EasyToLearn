package com.example.easytolearn.model.bookAndDelivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookModel implements BaseBookModel {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private Long userId;
}
