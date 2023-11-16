package com.iwa.jobservice.matching;

public class Establishment {
    private Long id;

    private String city;

    public Establishment() {
    }

    public Establishment(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
