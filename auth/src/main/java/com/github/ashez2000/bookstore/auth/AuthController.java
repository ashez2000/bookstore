package com.github.ashez2000.bookstore.auth;

import com.github.ashez2000.bookstore.auth.dto.AuthResponse;
import com.github.ashez2000.bookstore.auth.dto.RegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/api/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDto data) {
        var r = authService.register(data) ;
        return ResponseEntity.ok().body(r);
    }

    @PostMapping("/api/login")
    public ResponseEntity<AuthResponse> login(@RequestBody RegisterDto data) throws Exception {
        var r = authService.login(data);
        return ResponseEntity.ok().body(r);
    }

}
