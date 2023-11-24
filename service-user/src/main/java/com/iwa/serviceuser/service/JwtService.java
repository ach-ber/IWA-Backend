package com.iwa.serviceuser.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    public static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 30; // 30 minutes
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (MalformedJwtException e) {
            throw new JwtException("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            throw new JwtException("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            throw new JwtException("JWT claims string is empty -> Message: {}", e);
        }
    }

    public String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
