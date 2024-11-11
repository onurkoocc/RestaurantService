
package com.qrmenu.RestaurantService.config.security;

import com.qrmenu.RestaurantService.config.security.dto.UserIdentity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    private final SecretKey key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        if (secret.length() < 64) {
            throw new IllegalArgumentException("Secret key must be at least 64 characters long for HS512");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Log the exception if necessary
            return false;
        }
    }

    public UserIdentity getUserIdentityFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        UUID userId = UUID.fromString(claims.get("userId", String.class));
        String username = claims.getSubject();
        String role = claims.get("role", String.class);
        return new UserIdentity(userId, username, role);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
