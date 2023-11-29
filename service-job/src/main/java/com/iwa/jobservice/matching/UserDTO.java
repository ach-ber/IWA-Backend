package com.iwa.jobservice.matching;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String id;
    private String email;
    private String password;
    private String role;
    private String id_recruiter;

    public UserDTO() {
    }

    public UserDTO(String id, String email, String password, String role, String id_recruiter) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id_recruiter = id_recruiter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId_recruiter() {
        return id_recruiter;
    }

    public void setId_recruiter(String id_recruiter) {
        this.id_recruiter = id_recruiter;
    }
}
