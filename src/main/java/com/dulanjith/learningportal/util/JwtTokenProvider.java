package com.dulanjith.learningportal.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        SecretKey key = generateSecretKey();

        return Jwts.builder()
                .issuer("Learning Portal")
                .subject("Jwt Token")
                .claim("username", username)
                .claim("authorities", authorities)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key).compact();
    }

    public Claims validateToken(String token) {
        SecretKey key = generateSecretKey();
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
