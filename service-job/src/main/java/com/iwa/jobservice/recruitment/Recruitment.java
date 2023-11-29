package com.iwa.jobservice.recruitment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recruiter_key;

    private Long candidate_key;

    private Long job_key;

    public Recruitment(Long id, Long recruiter_key, Long candidate_key, Long job_key) {
        this.id = id;
        this.recruiter_key = recruiter_key;
        this.candidate_key = candidate_key;
        this.job_key = job_key;
    }

    public Recruitment(Long recruiter_key, Long candidate_key, Long job_key) {
        this.recruiter_key = recruiter_key;
        this.candidate_key = candidate_key;
        this.job_key = job_key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecruiter_key() {
        return recruiter_key;
    }

    public void setRecruiter_key(Long recruiter_key) {
        this.recruiter_key = recruiter_key;
    }

    public Long getCandidate_key() {
        return candidate_key;
    }

    public void setCandidate_key(Long candidate_key) {
        this.candidate_key = candidate_key;
    }

    public Long getJob_key() {
        return job_key;
    }

    public void setJob_key(Long job_key) {
        this.job_key = job_key;
    }
}
