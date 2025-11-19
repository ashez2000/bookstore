package com.github.ashez2000.bookstore.books.controller;

import com.github.ashez2000.bookstore.books.dto.BookDto;
import com.github.ashez2000.bookstore.books.dto.CreateBookDto;
import com.github.ashez2000.bookstore.books.dto.QuantityDto;
import com.github.ashez2000.bookstore.books.entity.Book;
import com.github.ashez2000.bookstore.books.exception.ResourceNotFoundException;
import com.github.ashez2000.bookstore.books.mapper.BookMapper;
import com.github.ashez2000.bookstore.books.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        var books = bookService.getBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id) throws ResourceNotFoundException {
        var book = bookService.getBook(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", Long.toString(id))
        );

        return ResponseEntity.status(HttpStatus.OK).body(BookMapper.toBookDto(book));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookDto data) {
        var book = bookService.createBook(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookMapper.toBookDto(book));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("{id}/reserve")
    public ResponseEntity<String> reserve(@PathVariable("id") Long id, @RequestBody QuantityDto body) {
        bookService.reserve(id, body.getQuantity());
        return ResponseEntity.status(HttpStatus.OK).body("Reserved");
    }

    @PostMapping("{id}/release")
    public ResponseEntity<String> release(@PathVariable("id") Long id, @RequestBody QuantityDto body) {
        bookService.release(id, body.getQuantity());
        return ResponseEntity.status(HttpStatus.OK).body("Released");
    }
}
