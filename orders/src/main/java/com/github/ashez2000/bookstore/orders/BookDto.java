package com.github.ashez2000.bookstore.orders;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private String author;
    private Integer inventoryCount;
}
