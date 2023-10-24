package com.awi.iwabackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    private Integer id;
    private String streetnum;
    private String street;
    private String complement;
    private String zipcode;
    private String city;
    private String country;

    public Address() {
    }

    public Address(Integer id, String streetnum, String street, String complement, String zipcode, String city, String country) {
        this.id = id;
        this.streetnum = streetnum;
        this.street = street;
        this.complement = complement;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetnum() {
        return streetnum;
    }

    public void setStreetnum(String streetnum) {
        this.streetnum = streetnum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
