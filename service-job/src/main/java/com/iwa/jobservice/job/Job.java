package com.iwa.jobservice.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long startDate; // long for timestamp

    private Long endDate; // long for timestamp

    private String perks;

    private Float salary;

    private Long category_key;

    private Long establishment_key;

    public Job(){
    }

    public Job(Long id, String name, Long startDate, Long endDate, String perks, Float salary, Long category_key, Long establishment_key) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.perks = perks;
        this.salary = salary;
        this.category_key = category_key;
        this.establishment_key = establishment_key;
    }

    public Job(String name, Long startDate, Long endDate, String perks, Float salary, Long category_key, Long establishment_key) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.perks = perks;
        this.salary = salary;
        this.category_key = category_key;
        this.establishment_key = establishment_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getPerks() {
        return perks;
    }

    public void setPerks(String perks) {
        this.perks = perks;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Long getCategory_key() {
        return category_key;
    }

    public void setCategory_key(Long category_key) {
        this.category_key = category_key;
    }

    public Long getEstablishment_key() {
        return establishment_key;
    }

    public void setEstablishment_key(Long establishment_key) {
        this.establishment_key = establishment_key;
    }
}
