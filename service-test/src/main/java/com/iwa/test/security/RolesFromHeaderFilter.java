package com.iwa.test.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
        System.out.println("doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String rolesHeader = httpRequest.getHeader(ROLES_HEADER);

        if (rolesHeader != null) {
            System.out.println("rolesHeaderrr: " + rolesHeader);
            List<GrantedAuthority> authorities = Arrays.stream(rolesHeader.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            // Créer une instance d'Authentication avec les rôles extraits et stocker dans le contexte de sécurité
            //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Authentication newAuthentication = new CustomAuthentication("username", "", authorities);
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
            System.out.println("authentication: " + newAuthentication);
        }
        else {
            System.out.println("rolesHeader is null");
        }

        chain.doFilter(request, response);
    }

}