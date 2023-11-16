package com.iwa.serviceuser.service;

import com.iwa.serviceuser.entity.UserCredential;
import com.iwa.serviceuser.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) throws AuthenticationException {
        Optional<UserCredential> user = repository.findByEmail(credential.getEmail());
        if (user.isPresent()) {
            throw new AuthenticationException("user already exist") {};
        }
        else {
            credential.setPassword(passwordEncoder.encode(credential.getPassword()));
            repository.save(credential);
            return "user added to the system";
        }
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
