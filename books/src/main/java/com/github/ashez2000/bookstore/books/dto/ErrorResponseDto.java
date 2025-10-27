package com.github.ashez2000.bookstore.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String path;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
