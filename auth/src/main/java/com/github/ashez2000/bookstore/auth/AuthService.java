package com.github.ashez2000.bookstore.auth;

import com.github.ashez2000.bookstore.auth.dto.RegisterDto;
import com.github.ashez2000.bookstore.auth.dto.AuthResponse;
import com.github.ashez2000.bookstore.auth.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(/*strength*/ 10);

    private UserRepository userRepository;

    public AuthResponse register(RegisterDto data) {
        String hash = hashPassword(data.getPassword());

        User u = new User();
        u.setEmail(data.getEmail());
        u.setPassword(hash);
        u.setRole("USER");

        u = userRepository.save(u);

        // TODO: Generate JWT Token
        String token = "jwt_token";

        return new AuthResponse(
                new UserDto(u.getId(), u.getEmail(), u.getRole()),
                token
        );
    }

    public AuthResponse login(RegisterDto data) throws Exception {
        // Check email
        String email = data.getEmail();
        User u = userRepository.findByEmail(email).orElseThrow(() -> new Exception("Invalid Credentials"));

        // Verify password
        if (!verifyPassword(data.getPassword(), u.getPassword())) {
            throw new Exception("Invalid Credentials");
        }

        // TODO: Generate JWT Token
        String token = "jwt_token";

        return new AuthResponse(
                new UserDto(u.getId(), u.getEmail(), u.getRole()),
                token
        );
    }

    private String hashPassword(String password) {
        return encoder.encode(password);
    }

    private boolean verifyPassword(String password, String hash) {
        return encoder.matches(password, hash);
    }
}
