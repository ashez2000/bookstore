package com.github.ashez2000.bookstore.orders;

import com.github.ashez2000.bookstore.orders.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("books")
public interface BookFeignClient {

    @GetMapping("/api/books/{id}")
    ResponseEntity<BookDto> getBook(@PathVariable("id") Long id);
}
