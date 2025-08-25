package com.github.ashez2000.bookstore.books;

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
        book.setTitle(dto.title);
        book.setDescription(dto.description);
        book.setAuthor(dto.author);
        book.setInventoryCount(10);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
