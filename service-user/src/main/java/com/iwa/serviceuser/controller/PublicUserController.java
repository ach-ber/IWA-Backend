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
@RequestMapping("/api/public")
public class PublicUserController {
    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody SignUpRequest signupRequest, BindingResult result) throws AuthenticationException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        try {
            String response = service.saveUser(signupRequest);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user already exist");
        }
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@Valid @RequestBody SignInRequest signInRequest, BindingResult result) throws AuthenticationException {
        Authentication authenticate;
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return ResponseEntity.ok(service.generateToken(signInRequest.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?> userInfo(@RequestHeader("X-User-Email") String email) {
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return service.getInfosUser(email);
    }
}
