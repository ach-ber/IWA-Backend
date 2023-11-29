package com.iwa.serviceuser.service;
import com.iwa.serviceuser.dto.SignUpRequest;
import com.iwa.serviceuser.entity.User;
import com.iwa.serviceuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    public String saveUser(SignUpRequest signupRequest) throws AuthenticationException {
        Optional<User> user = repository.findByEmail(signupRequest.getEmail());
        if (user.isPresent()) {
            throw new AuthenticationException("user already exist") {};
        }
        else {
            String roleRequest = signupRequest.getRole();
            if (roleRequest == null) {
                roleRequest = "ROLE_FREE";
            } else {
                switch (roleRequest) {
                    case "ROLE_FREE":
                        break;
                    case "ROLE_SILVER":
                        break;
                    case "ROLE_GOLD":
                        break;
                    case "ROLE_PLATINUM":
                        break;
                    case "ROLE_ADMIN":
                        break;
                    default:
                        roleRequest = "ROLE_FREE";
                }
            }
            User newUser = new User();
            newUser.setEmail(signupRequest.getEmail());
            newUser.setRole(roleRequest);
            newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            newUser.setId_recruiter(signupRequest.getId_recruiter());
            repository.save(newUser);
            return "user added to the system";
        }
    }

    public String generateToken(String email) {
        String role;
        try {
            role = repository.findByEmail(email).get().getRole();
        } catch (Exception e) {
            throw new UsernameNotFoundException("user not found with name :" + email);
        }
        return jwtService.generateToken(email,role);
    }

    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }

    public ResponseEntity<?> getInfosUser(String email) {
        try {
            List<User> users = repository.findAll();
            User foundUser = users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + email));
            if (foundUser == null) {
                return ResponseEntity.ok("user is not a recruiter");
            }
            else {
                return ResponseEntity.ok(foundUser);
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("user not found with name :" + email);
        }
    }

}
