package com.example.easytolearn.model.bookAndDelivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookOrdersModel {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long bookId;
}
