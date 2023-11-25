package com.iwa.recruiterservice.recruiter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterUserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    private String password;

    @NotBlank
    @Size(max = 15)
    private String phone;

    @Email
    private String email;

    @NotBlank
    private LocalDate createdAt;

    @NotBlank
    private String subscription;

    @NotBlank
    private LocalDate subscription_startDate;

    private LocalDate subscription_endDate;
    private int company_id;
    private int[] establishments;

    public RecruiterUserRequest(String firstName, String lastName, String password, String phone, String email, LocalDate createdAt, String subscription, LocalDate subscription_startDate, LocalDate subscription_endDate, int company_id, int[] establishments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.subscription = subscription;
        this.subscription_startDate = subscription_startDate;
        this.subscription_endDate = subscription_endDate;
        this.company_id = company_id;
        this.establishments = establishments;
    }

}
