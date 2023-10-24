package com.awi.iwabackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private byte[] logo;
    private Integer siren;
    @ElementCollection
    @CollectionTable(name = "establishments", joinColumns = @JoinColumn(name = "company_id"))
    private List<Integer> establishments;

    public Company(Integer id, String name, byte[] logo, Integer siren, List<Integer> establishments) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.siren = siren;
        this.establishments = establishments;
    }

    public Company() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Integer getSiren() {
        return siren;
    }

    public void setSiren(Integer siren) {
        this.siren = siren;
    }

    public List<Integer> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<Integer> establishments) {
        this.establishments = establishments;
    }
}
