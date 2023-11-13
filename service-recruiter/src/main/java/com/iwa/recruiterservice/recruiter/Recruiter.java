package com.iwa.recruiterservice.recruiter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDate createdAt;
    private int subscription;
    private LocalDate subscription_startDate;
    private LocalDate subscription_endDate;
    private int company_id;
    private int[] establishments;

    public Recruiter() {
    }

    public Recruiter(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Recruiter(String firstName, String lastName, String phone, String email, LocalDate createdAt, int subscription, LocalDate subscription_startDate, LocalDate subscription_endDate, int company_id, int[] establishments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.subscription = subscription;
        this.subscription_startDate = subscription_startDate;
        this.subscription_endDate = subscription_endDate;
        this.company_id = company_id;
        this.establishments = establishments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getSubscription() {
        return subscription;
    }

    public void setSubscription(int subscription) {
        this.subscription = subscription;
    }

    public LocalDate getSubscription_startDate() {
        return subscription_startDate;
    }

    public void setSubscription_startDate(LocalDate subscription_startDate) {
        this.subscription_startDate = subscription_startDate;
    }

    public LocalDate getSubscription_endDate() {
        return subscription_endDate;
    }

    public void setSubscription_endDate(LocalDate subscription_endDate) {
        this.subscription_endDate = subscription_endDate;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int[] getEstablishments() {
        return establishments;
    }

    public void setEstablishments(int[] establishments) {
        this.establishments = establishments;
    }
}
