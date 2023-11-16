package com.iwa.jobservice.matching;

import com.iwa.jobservice.job.Job;
import com.iwa.jobservice.jobcategory.Jobcategory;

import java.util.List;

public class Availability {
    private Long id;

    private Job job;

    private Jobcategory jobcategory;

    private Long startsAt;

    private Long endsAt;

    private List<String> places;

    public Availability() {
    }

    public Availability(Long id, Job job, Jobcategory jobcategory, Long startsAt, Long endsAt, List<String> places) {
        this.id = id;
        this.job = job;
        this.jobcategory = jobcategory;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.places = places;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Jobcategory getJobcategory() {
        return jobcategory;
    }

    public void setJobcategory(Jobcategory jobcategory) {
        this.jobcategory = jobcategory;
    }

    public Long getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Long startsAt) {
        this.startsAt = startsAt;
    }

    public Long getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Long endsAt) {
        this.endsAt = endsAt;
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }
}
