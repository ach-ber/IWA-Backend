package com.iwa.recruiterservice.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable {

    private Long id;
    private String streetNum;
    private String street;
    private String complement;
    private String city;
    private String zipCode;
    private String country;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String streetNum, String street, String complement, String city, String zipCode, String country) {
        this.id = id;
        this.streetNum = streetNum;
        this.street = street;
        this.complement = complement;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public AddressDTO(String streetNum, String street, String complement, String city, String zipCode, String country) {
        this.streetNum = streetNum;
        this.street = street;
        this.complement = complement;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return streetNum + " " +
                street +
                ", " + complement +
                ", " + city +
                ", " + country;
    }
}
