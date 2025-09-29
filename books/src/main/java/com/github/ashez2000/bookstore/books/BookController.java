package com.github.ashez2000.bookstore.books;

import com.github.ashez2000.bookstore.books.dto.BookDto;
import com.github.ashez2000.bookstore.books.dto.CreateBookDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        var books = bookService.getBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id) {
        var book = bookService.getBook(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(BookMapper.toBookDto(book));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody CreateBookDto data) {
        var book = bookService.createBook(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PostMapping("/{bookId}/reserve/{quantity}")
    public ResponseEntity<Void> reserve(@PathVariable Long bookId, @PathVariable Integer quantity) throws Exception {
        bookService.reserve(bookId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
