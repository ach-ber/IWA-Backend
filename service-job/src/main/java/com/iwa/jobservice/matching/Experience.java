package com.iwa.jobservice.matching;

import com.iwa.jobservice.job.Job;
import com.iwa.jobservice.jobcategory.Jobcategory;

public class Experience {
    private Long id;

    private Job job;

    private Jobcategory jobcategory;

    private Long startedAt;

    private Long endedAt;

    public Experience() {
    }

    public Experience(Job job, Jobcategory jobcategory, Long startedAt, Long endedAt) {
        this.job = job;
        this.jobcategory = jobcategory;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
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

    public Long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Long startedAt) {
        this.startedAt = startedAt;
    }

    public Long getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Long endedAt) {
        this.endedAt = endedAt;
    }
}
