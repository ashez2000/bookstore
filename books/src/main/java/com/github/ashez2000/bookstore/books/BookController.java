package com.github.ashez2000.bookstore.books;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        var books = bookService.getBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/:id")
    public ResponseEntity<Optional<Book>> getBook(@PathParam("id") Long id) {
        var book = bookService.getBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookDto data) {
        var book = bookService.createBook(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBook(@PathParam("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
