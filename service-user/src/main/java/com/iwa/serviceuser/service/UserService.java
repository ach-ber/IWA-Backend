package com.iwa.serviceuser.service;
import com.iwa.serviceuser.dto.SignUpRequest;
import com.iwa.serviceuser.entity.User;
import com.iwa.serviceuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {

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
            User newUser = new User();
            newUser.setEmail(signupRequest.getEmail());
            newUser.setRole(roleRequest);
            newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
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


}
