package com.iwa.recruiterservice.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class CustomAuthentication extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;
    private final Collection<GrantedAuthority> authorities;

    public CustomAuthentication(Object principal, Object credentials, Collection<GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.authorities = authorities;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


}