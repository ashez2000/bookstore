package com.github.ashez2000.bookstore.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private UserDto user;
    private String token;
}
