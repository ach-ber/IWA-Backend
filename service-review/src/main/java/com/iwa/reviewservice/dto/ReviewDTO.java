package com.iwa.reviewservice.dto;

import com.iwa.reviewservice.review.Review;

public class ReviewDTO {
    private Review review;
    private CandidateDTO candidate;
    private JobDTO job;
    private RecruiterDTO recruiter;

    public ReviewDTO() {
    }

    public ReviewDTO(Review review, CandidateDTO candidate, JobDTO job, RecruiterDTO recruiter) {
        this.review = review;
        this.candidate = candidate;
        this.job = job;
        this.recruiter = recruiter;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public CandidateDTO getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateDTO candidate) {
        this.candidate = candidate;
    }

    public JobDTO getJob() {
        return job;
    }

    public void setJob(JobDTO job) {
        this.job = job;
    }

    public RecruiterDTO getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(RecruiterDTO recruiter) {
        this.recruiter = recruiter;
    }
}
