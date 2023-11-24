package com.iwa.serviceuser.controller;

import com.iwa.serviceuser.dto.SignInRequest;
import com.iwa.serviceuser.dto.SignUpRequest;
import com.iwa.serviceuser.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
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
    @GetMapping("/userInfo")
    public ResponseEntity<String> userInfo(@RequestHeader("X-User-Roles") String roles) {
        if (roles == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.ok("User role: " + roles);
    }
}
