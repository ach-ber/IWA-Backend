package com.iwa.serviceuser.controller;

import com.iwa.serviceuser.service.JwtService;
import com.iwa.serviceuser.service.JwtServiceTest;
import com.iwa.serviceuser.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProtectedUserControllerTest {

    @Autowired
    private JwtService jwtService;

    @Test
    public void validateToken() {
        String token = jwtService.generateToken("test", "ROLE_USER");
        boolean result = jwtService.validateToken(token);
        assertTrue(result);
    }
}
