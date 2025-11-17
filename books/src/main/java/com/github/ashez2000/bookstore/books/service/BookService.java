package com.github.ashez2000.bookstore.books.service;

import com.github.ashez2000.bookstore.books.dto.CreateBookDto;
import com.github.ashez2000.bookstore.books.entity.Book;
import com.github.ashez2000.bookstore.books.entity.Inventory;
import com.github.ashez2000.bookstore.books.repository.BookRepository;
import com.github.ashez2000.bookstore.books.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    private InventoryRepository inventoryRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book createBook(CreateBookDto dto) {
        var book = new Book();
        var inventory = new Inventory();

        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setAuthor(dto.getAuthor());

        book = bookRepository.save(book);

        inventory.setProductId(book.getId());
        inventory.setStock(10);
        inventoryRepository.save(inventory);

        return book;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
