package com.iwa.gateway.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${service.gateway.SECRET}")
    private String SECRET;
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

    public String getRole(final String token) {
        String role = (String) Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().get("role");
        System.out.println("role: " + role);
        return role;
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
