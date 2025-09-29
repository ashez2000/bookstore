package com.github.ashez2000.bookstore.books.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private String author;
    private Integer stock;
}
