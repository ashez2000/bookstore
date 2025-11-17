package com.github.ashez2000.bookstore.books.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus statusCode;

    public ApiException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
