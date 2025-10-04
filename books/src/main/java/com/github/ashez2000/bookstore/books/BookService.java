package com.github.ashez2000.bookstore.books;

import com.github.ashez2000.bookstore.books.dto.CreateBookDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(CreateBookDto dto) {
        var book = new Book();
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setAuthor(dto.getAuthor());
        book.setStock(10);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void reserve(Long bookId, Integer quantity) throws Exception {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found"));

        if (book.getStock() < quantity) {
            throw new Exception("Not enough stock available");
        }

        book.setStock(book.getStock() - quantity);
        bookRepository.save(book);
    }
}
