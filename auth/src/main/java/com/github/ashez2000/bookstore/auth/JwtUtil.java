package com.github.ashez2000.bookstore.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(@Value("${app.jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(JwtPayload payload) {
        return Jwts.builder()
                .claim("id", payload.getId())
                .claim("email", payload.getEmail())
                .claim("role", payload.getRole())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Validate token
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public JwtPayload extractAndValidate(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new Exception("Unauthorized");
        }

        try {
            Claims c = Jwts.parser()
                    .setSigningKey(key) // use Keys.hmacShaKeyFor(secretKey.getBytes()) for HS256+
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return new JwtPayload(
                    c.get("id", Long.class),
                    c.get("email", String.class),
                    c.get("role", String.class)
            );
        } catch (JwtException e) {
            throw new JwtException("JWT validation failed: " + e.getMessage());
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Data
    @AllArgsConstructor
    public static class JwtPayload {
        private Long id;
        private String email;
        private String role;
    }

}
