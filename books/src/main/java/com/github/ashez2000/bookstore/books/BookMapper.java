package com.github.ashez2000.bookstore.books;

import com.github.ashez2000.bookstore.books.dto.BookDto;

public class BookMapper {
    public static BookDto toBookDto(Book b) {
        var d = new BookDto();
        d.setId(b.getId());
        d.setTitle(b.getTitle());
        d.setDescription(b.getDescription());
        d.setAuthor(b.getAuthor());
        d.setStock(b.getStock());
        return d;
    }
}
