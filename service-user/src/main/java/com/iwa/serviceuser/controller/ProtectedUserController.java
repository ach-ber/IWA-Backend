package com.iwa.serviceuser.controller;

import com.iwa.serviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/protected")
public class ProtectedUserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        if (service.validateToken(token)) {
            return "valid token";
        }
        else {
            return "invalid token";
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }
}
