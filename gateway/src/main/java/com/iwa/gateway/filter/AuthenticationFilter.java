package com.iwa.gateway.filter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.iwa.gateway.config.RouteValidator;
import com.iwa.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationFilter implements GatewayFilter {

    private final Cache<String, Boolean> revokedTokenCache;

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        revokedTokenCache = CacheBuilder.newBuilder()
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // disable cors and csrf
        exchange.getRequest().mutate()
                .header(HttpHeaders.ORIGIN, "*")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*")
                .header(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

        if (validator.isSecured.test(exchange.getRequest())) {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Missing authorization header");
            }

            String authHeader = extractAuthorizationHeader(exchange.getRequest());
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid authorization header");
            }

            authHeader = authHeader.substring(7);

            try {
                if (!jwtUtil.validateToken(authHeader)) {
                    return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid or expired token");
                }

                if (isTokenRevoked(authHeader)) {
                    return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Token revoked");
                }

                if (requestRequiresLogout(exchange.getRequest())) {
                    revokeToken(authHeader);
                }

                String role = jwtUtil.getRole(authHeader);
                String email = jwtUtil.getEmail(authHeader);
                exchange.getRequest().mutate().header("X-User-Roles", role);
                exchange.getRequest().mutate().header("X-User-Email", email);
                System.out.println("request: " + exchange.getRequest().getHeaders());

            } catch (Exception e) {
                return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid or expired token");
            }
        }

        return chain.filter(exchange);
    }

    private Mono<Void> setErrorResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory().wrap(message.getBytes())));
    }

    private String extractAuthorizationHeader(ServerHttpRequest request) {
        return request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }

    private boolean requestRequiresLogout(ServerHttpRequest request) {
        return request.getURI().getPath().contains("logout");
    }

    private void revokeToken(String authToken) {
        revokedTokenCache.put(authToken, true);
    }

    public boolean isTokenRevoked(String token) {
        return revokedTokenCache.getIfPresent(token) != null;
    }
}