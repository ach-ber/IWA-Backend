package com.iwa.serviceuser.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.iwa.serviceuser.entity.User;
import com.iwa.serviceuser.repository.UserRepository;
import com.iwa.serviceuser.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    public void testGenerateToken() {
        String email = "JohnDoe@gmail.com";
        String role = "ROLE_USER";
        String generatedToken = jwtService.generateToken(email, role);
        assertTrue(jwtService.validateToken(generatedToken));
    }
}

