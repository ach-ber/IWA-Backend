package com.iwa.serviceuser.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank

    private String role;

    @NotBlank
    @Size(min = 6)
    private String password;

}

