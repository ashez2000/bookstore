package com.github.ashez2000.bookstore.orders;

import com.github.ashez2000.bookstore.orders.dto.BookDto;
import com.github.ashez2000.bookstore.orders.dto.QuantityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("books")
public interface BookFeignClient {

    @GetMapping("/api/books/{id}")
    ResponseEntity<BookDto> getBook(@PathVariable("id") Long id);

    @PostMapping("/api/books/{id}/reserve")
    ResponseEntity<String> reserve(@PathVariable("id") Long id, @RequestBody QuantityDto body);

    @PostMapping("/api/books/{id}/release")
    ResponseEntity<String> release(@PathVariable("id") Long id, @RequestBody QuantityDto body);

}
