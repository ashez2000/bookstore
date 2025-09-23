package com.github.ashez2000.bookstore.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping
    public ResponseEntity<String> profile() {
        return ResponseEntity.ok().body("User profile");
    }

}
