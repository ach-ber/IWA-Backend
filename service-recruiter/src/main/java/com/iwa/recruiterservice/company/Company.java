package com.iwa.recruiterservice.company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int siren;
    private int[] establishments;

    public Company() {
    }

    public Company(String name, int siren, int[] establishments) {
        this.name = name;
        this.siren = siren;
        this.establishments = establishments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSiren() {
        return siren;
    }

    public void setSiren(int siren) {
        this.siren = siren;
    }

    public int[] getEstablishments() {
        return establishments;
    }

    public void setEstablishments(int[] establishments) {
        this.establishments = establishments;
    }
}
