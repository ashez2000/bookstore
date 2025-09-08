package com.github.ashez2000.bookstore.books;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class BookDto {
    Long id;
    String title;
    String description;
    String author;
    Integer inventoryCount;
}
