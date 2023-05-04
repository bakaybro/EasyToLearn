package com.example.easytolearn.model.order;

import com.example.easytolearn.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemsForBuyModel {
    List<Book> bookList;
    Long userId;
}
