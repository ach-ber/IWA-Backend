package com.iwa.reviewservice.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AvailabilityDTO implements Serializable {
    private String job;
    private int jobCategory;
    private LocalDate startsAt;
    private LocalDate endsAt;
    private List<AddressDTO> places;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(String job, int jobCategory, LocalDate startsAt, LocalDate endsAt, List<AddressDTO> places) {
        this.job = job;
        this.jobCategory = jobCategory;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.places = places;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(int jobCategory) {
        this.jobCategory = jobCategory;
    }

    public LocalDate getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDate startsAt) {
        this.startsAt = startsAt;
    }

    public LocalDate getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDate endsAt) {
        this.endsAt = endsAt;
    }

    public List<AddressDTO> getPlaces() {
        return places;
    }

    public void setPlaces(List<AddressDTO> places) {
        this.places = places;
    }
}
