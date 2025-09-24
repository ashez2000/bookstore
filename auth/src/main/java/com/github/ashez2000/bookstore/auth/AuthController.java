package com.github.ashez2000.bookstore.auth;

import com.github.ashez2000.bookstore.auth.dto.AuthResponse;
import com.github.ashez2000.bookstore.auth.dto.RegisterDto;
import com.github.ashez2000.bookstore.auth.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
    private JwtUtil jwtUtil;

    @PostMapping("/api/auth/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDto data) {
        var r = authService.register(data) ;
        return ResponseEntity.ok().body(r);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody RegisterDto data) throws Exception {
        var r = authService.login(data);
        return ResponseEntity.ok().body(r);
    }

    @GetMapping("/api/auth/profile")
    public ResponseEntity<UserDto> profile(HttpServletRequest req) throws Exception {
        var auth = jwtUtil.extractAndValidate(req);
        var u = authService.getUserProfile(auth.getId());
        return ResponseEntity.ok().body(u);
    }

}
