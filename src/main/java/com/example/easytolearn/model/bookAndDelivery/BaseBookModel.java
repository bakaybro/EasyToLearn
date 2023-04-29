package com.example.easytolearn.model.bookAndDelivery;

import java.math.BigDecimal;

public interface BaseBookModel {
    Long getId();
    String getTitle();
    String getAuthor();
    String getPublisher();
    Integer getYear();
    String getDescription();
    BigDecimal getPrice();
    Long getCategoryId();
    Long getUserId();
}
