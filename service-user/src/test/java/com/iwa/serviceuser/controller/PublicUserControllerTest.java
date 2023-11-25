package com.iwa.serviceuser.controller;

import com.iwa.serviceuser.entity.User;
import com.iwa.serviceuser.repository.UserRepository;
import com.iwa.serviceuser.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PublicUserControllerTest {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addNewUser() {
        User user = new User();
        String email = "test@gmail.com";
        user.setEmail(email);
        user.setPassword("password123");
        user.setRole("ROLE_USER");
        userRepository.save(user);
        assertTrue(userRepository.findByEmail(email).isPresent());
    }
}
