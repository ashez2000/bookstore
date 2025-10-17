package com.github.ashez2000.bookstore.books;

import com.github.ashez2000.bookstore.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
