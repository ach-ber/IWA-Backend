package com.iwa.jobservice.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(
                                        "/api/public/**"
                                ).permitAll()
                                .requestMatchers(
                                        "/api/protected/**"
                                ).authenticated()

                ).addFilterBefore(
                        authenticationTokenFilterBean(),
                        UsernamePasswordAuthenticationFilter.class
                ).exceptionHandling(
                        exception -> exception.accessDeniedHandler(accessDeniedHandler())
                );
        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    private static class CustomAccessDeniedHandler implements AccessDeniedHandler {


        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("Accès refusé - Vous n'avez pas les droits requis pour accéder à cette ressource.");
        }
    }

    @Bean
    public RolesFromHeaderFilter authenticationTokenFilterBean() throws Exception {
        return new RolesFromHeaderFilter();
    }
}