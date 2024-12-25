package com.dulanjith.learningportal.filter;

import com.dulanjith.learningportal.constant.ApplicationConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null != authentication) {
            Environment environment = getEnvironment();

            String secret = environment.getProperty(ApplicationConstants.JWT_SECRET,
                    ApplicationConstants.JWT_SECRET_DEFAULT);

            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            String jwtToken = Jwts.builder()
                    .issuer("Learning Portal")
                    .subject("Jwt Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 604800000)) // 1 week expiration
                    .signWith(secretKey).compact();

            response.setHeader(ApplicationConstants.JWT_HEADER, "Bearer " + jwtToken);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user/login");
        //This filter will execute only when a user tried to log in for the first time
    }
}
