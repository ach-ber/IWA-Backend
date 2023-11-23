package com.iwa.serviceuser.controller;

import com.iwa.serviceuser.dto.AuthRequest;
import com.iwa.serviceuser.dto.SignupRequest;
import com.iwa.serviceuser.entity.UserCredential;
import com.iwa.serviceuser.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody SignupRequest signupRequest) throws AuthenticationException {
        System.out.println("signupRequest: " + signupRequest);
        try {
            String response = service.saveUser(signupRequest);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user already exist");
        }
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        // Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            return ResponseEntity.ok(service.generateToken(authRequest.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid access");
        }
        /*
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getEmail());
        } else {
            throw new RuntimeException("invalid access");
        }
         */
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        System.out.println("login");
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            System.out.println("auth: " + SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/";
        }
        else {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        /*
        HttpSession session = request.getSession();
        System.out.println("session: " + session);
        session.invalidate();
        SecurityContextHolder.clearContext();
        System.out.println("security context: " + SecurityContextHolder.getContext());
        return "redirect:/login";

         */
        return "logout";
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestHeader("X-User-Roles") String roles) {
        return "roles USER: " + roles;
    }

    @GetMapping("/testss")
    public String test() {
        return "test";
    }
}
