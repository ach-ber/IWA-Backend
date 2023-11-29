package com.iwa.jobservice.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class RolesFromHeaderFilter extends OncePerRequestFilter {

    private static final String ROLES_HEADER = "X-User-Roles";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String rolesHeader = ((HttpServletRequest) request).getHeader(ROLES_HEADER);
        if (rolesHeader != null) {
            SecurityContextHolder.clearContext();
            List<GrantedAuthority> authorities = Arrays.stream(rolesHeader.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            Authentication newAuthentication = new CustomAuthentication("username", "", authorities);
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }
        chain.doFilter(request, response);
    }
}