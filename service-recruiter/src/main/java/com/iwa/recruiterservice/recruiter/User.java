package com.iwa.recruiterservice.recruiter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private String role;

    @NotBlank
    @Size(min = 6)
    private String password;

    private Long id_recruiter;

    public User(RecruiterUserRequest recruiterUserRequest) {
        this.email = recruiterUserRequest.getEmail();
        this.role = recruiterUserRequest.getSubscription();
        this.password = recruiterUserRequest.getPassword();
    }

}

