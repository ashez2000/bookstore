package com.github.ashez2000.bookstore.books;

public class BookMapper {
    public static BookDto toBookDto(Book b) {
        var d = new BookDto();
        d.setId(b.getId());
        d.setTitle(b.getTitle());
        d.setDescription(b.getDescription());
        d.setAuthor(b.getAuthor());
        d.setInventoryCount(b.getInventoryCount());
        return d;
    }
}
