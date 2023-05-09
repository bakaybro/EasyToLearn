package com.example.easytolearn.model.bookAndDelivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookImageModel {
    private Long id;
    private String bookImageUrl;
    private Long bookId;
}
