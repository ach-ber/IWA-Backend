package com.iwa.jobservice.matching;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExperienceDTO implements Serializable {
    private String job;
    private int jobCategory;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private EstablishmentDTO establishment;

    public ExperienceDTO() {
    }

    public ExperienceDTO(String job, int jobCategory, LocalDateTime startedAt, LocalDateTime endedAt, EstablishmentDTO establishment) {
        this.job = job;
        this.jobCategory = jobCategory;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.establishment = establishment;
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

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public EstablishmentDTO getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentDTO establishment) {
        this.establishment = establishment;
    }
}