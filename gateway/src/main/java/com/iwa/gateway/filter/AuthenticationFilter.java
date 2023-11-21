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

    public AuthenticationFilter() {
        revokedTokenCache = CacheBuilder.newBuilder()
                .expireAfterWrite(24, TimeUnit.HOURS) // Exemple d'expiration après 24 heures
                .build();
    }
    private final Cache<String, Boolean> revokedTokenCache;

    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (validator.isSecured.test(exchange.getRequest())) {
            //header contains token or not
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Missing authorization header");
            }

            String authHeader;
            try {
                authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);

                    try {
//                    //REST call to AUTH service
                        // template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                        System.out.println("validate:" + authHeader);
                        if (!jwtUtil.validateToken(authHeader)) {
                            return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid or expired token");
                        }
                        if (isTokenRevoked(authHeader)) {
                            return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "token revoked");
                        }
                        System.out.println("Token not revoked...!");
                        System.out.println("revoqueTokenCache: " + revokedTokenCache.asMap());
                        if (requestRequiresLogout(exchange.getRequest())) {
                            System.out.println("logout...!");
                            revokeToken(authHeader);
                        }
                        System.out.println("valid access...!");
                        String role = jwtUtil.getRole(authHeader);
                        System.out.println("header:" + exchange.getRequest().getHeaders());
                        exchange.getRequest().mutate().header("X-User-Roles", role);
                        System.out.println("X-User-Roles: " + role);

                    } catch (Exception e) {
                        return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid or expired token");
                    }
                } else {
                    return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid authorization header");
                }
            } catch (Exception e) {
                return setErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid authorization header");
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

    private boolean requestRequiresLogout(ServerHttpRequest request) {
        // Vérifiez si la requête nécessite une déconnexion (logout) selon votre logique
        // Par exemple, selon le chemin de la requête
        return request.getURI().getPath().contains("logout");
    }

    private void revokeToken(String authToken) {
        // Révoquer le token en l'ajoutant à la liste noire
        revokedTokenCache.put(authToken, true);
    }

    public boolean isTokenRevoked(String token) {
        return revokedTokenCache.getIfPresent(token) != null;
    }

}