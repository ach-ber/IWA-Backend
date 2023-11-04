package com.iwa.test.recruiter;

import jakarta.persistence.*;
import java.util.List;

import java.time.LocalDate;
@Entity
@Table(name = "recruiters")
public class Recruiter {

    @Id
    @GeneratedValue
    private Integer id;
    private String lastname;
    private String firstname;
    private byte[] photo;
    private String phone;
    private String email;
    private LocalDate created_at;
    private String subscription;
    private LocalDate sub_startDate;
    private LocalDate sub_endDate;
    private int company_key;
    @ElementCollection
    @CollectionTable(name = "establishments", joinColumns = @JoinColumn(name = "recruiter_id"))
    private List<Integer> establishments;

    public Recruiter() {
    }

    public Recruiter(Integer id, String lastname, String firstname, byte[] photo, String phone, String email, LocalDate created_at, String subscription, LocalDate sub_startDate, LocalDate sub_endDate, int company_key, List<Integer> establishments) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
        this.created_at = created_at;
        this.subscription = subscription;
        this.sub_startDate = sub_startDate;
        this.sub_endDate = sub_endDate;
        this.company_key = company_key;
        this.establishments = establishments;
    }

    public Recruiter(String lastname, String firstname, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public LocalDate getSub_startDate() {
        return sub_startDate;
    }

    public void setSub_startDate(LocalDate sub_startDate) {
        this.sub_startDate = sub_startDate;
    }

    public LocalDate getSub_endDate() {
        return sub_endDate;
    }

    public void setSub_endDate(LocalDate sub_endDate) {
        this.sub_endDate = sub_endDate;
    }

    public int getCompany_key() {
        return company_key;
    }

    public void setCompany_key(int company_key) {
        this.company_key = company_key;
    }

    public List<Integer> getEstablishment() {
        return establishments;
    }

    public void setEstablishment(List<Integer> establishments) {
        this.establishments = Recruiter.this.establishments;
    }
}