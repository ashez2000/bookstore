package com.github.ashez2000.bookstore.books.service;

import com.github.ashez2000.bookstore.books.dto.CreateBookDto;
import com.github.ashez2000.bookstore.books.entity.Book;
import com.github.ashez2000.bookstore.books.entity.Inventory;
import com.github.ashez2000.bookstore.books.exception.ApiException;
import com.github.ashez2000.bookstore.books.exception.ResourceNotFoundException;
import com.github.ashez2000.bookstore.books.repository.BookRepository;
import com.github.ashez2000.bookstore.books.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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

    public void updateStock(long bookId, int quantity, String type) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                if (type.equals("reserve")) {
                    reserve(bookId, quantity);
                } else {
                    release(bookId, quantity);
                }

                return;
            } catch (ObjectOptimisticLockingFailureException e) {
                attempt++;
                if (attempt == maxRetries) throw e;
            }
        }
    }

    @Transactional
    public void reserve(long bookId, int quantity) {
        var inventory = inventoryRepository.findById(bookId).orElseThrow(() ->
                new ResourceNotFoundException("Inventory", "bookId", Long.toString(bookId)));

        if (inventory.getStock() < quantity) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Insufficient stock for bookId: " + bookId);
        }

        inventory.setStock(inventory.getStock() - quantity);
        inventoryRepository.save(inventory);
    }


    @Transactional
    public void release(long bookId, int quantity) {
        var inventory = inventoryRepository.findById(bookId).orElseThrow(() ->
                new ResourceNotFoundException("Inventory", "bookId", Long.toString(bookId)));
        inventory.setStock(inventory.getStock() + quantity);
        inventoryRepository.save(inventory);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
