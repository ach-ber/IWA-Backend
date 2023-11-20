package com.iwa.serviceuser.service;

import com.iwa.serviceuser.dto.SignupRequest;
import com.iwa.serviceuser.entity.UserCredential;
import com.iwa.serviceuser.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public String saveUser(SignupRequest signupRequest) throws AuthenticationException {
        Optional<UserCredential> user = repository.findByEmail(signupRequest.getEmail());
        if (user.isPresent()) {
            throw new AuthenticationException("user already exist") {};
        }
        else {

            String roleRequest = signupRequest.getRole();

            if (roleRequest == null) {
                roleRequest = "ROLE_USER";
            } else {
                switch (roleRequest) {
                    case "ROLE_ADMIN":
                        break;
                    case "ROLE_PREMIUM":
                        break;
                    default:
                        roleRequest = "ROLE_USER";
                }
            }

            UserCredential newUserCredential = new UserCredential();
            newUserCredential.setName(signupRequest.getName());
            newUserCredential.setEmail(signupRequest.getEmail());
            newUserCredential.setRole(roleRequest);
            newUserCredential.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            repository.save(newUserCredential);
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

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
