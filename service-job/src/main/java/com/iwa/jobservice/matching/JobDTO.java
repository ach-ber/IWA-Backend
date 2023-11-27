package com.iwa.jobservice.matching;

import com.iwa.jobservice.job.Job;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

public class JobDTO {
    private Job job;

    private String startDate;

    private String endDate;

    private String city;

    public JobDTO(Job job, String city) {
        this.job = job;
        this.city = city;
        this.startDate = convertToFormattedDate(job.getStartDate());
        this.endDate = convertToFormattedDate(job.getEndDate());
    }

    private static String convertToFormattedDate(Long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
