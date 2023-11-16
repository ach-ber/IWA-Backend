package com.iwa.jobservice.matching;

import com.iwa.jobservice.job.Job;
import com.iwa.jobservice.jobcategory.Jobcategory;

public class JobMatch {
    private Job job;

    private Jobcategory jobcategory;

    private Establishment establishment;

    public JobMatch(Job job, Establishment establishment, Jobcategory jobcategory) {
        this.job = job;
        this.establishment = establishment;
        this.jobcategory = jobcategory;
    }

    public Job getJob() {
        return job;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public Jobcategory getJobcategory() {
        return jobcategory;
    }
}
