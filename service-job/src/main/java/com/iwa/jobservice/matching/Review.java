package com.iwa.jobservice.matching;

public class Review implements Evaluation {
    private Long id;
    private String title;
    private int rating;
    private String comment;
    private Long recruiterId;
    private Long candidateId;
    private Long jobId;
    private Long createdAt;
    private String jobcategory;

    public Review() {
    }

    public Review(String title, int rating, String comment, Long recruiterId, Long candidateId, Long jobId, Long createdAt) {
        this.title = title;
        this.rating = rating;
        this.comment = comment;
        this.recruiterId = recruiterId;
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.createdAt = createdAt;
    }

    public Review(Review review, String jobcategory) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.recruiterId = review.getRecruiterId();
        this.candidateId = review.getCandidateId();
        this.jobId = review.getJobId();
        this.createdAt = review.getCreatedAt();
        this.jobcategory = jobcategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public double getValue() {
        switch (rating) {
            case 0:
                return -32/5 * 2;
            case 1:
                return -4 * 2;
            case 2:
                return -8/3 * 2;
            case 3:
                return 8/3 * 2;
            case 4:
                return 4 * 2;
            case 5:
                return 32/5 * 2;
            default:
                return 0;
        }
    }

    public String getJobcategory() {
        return jobcategory;
    }

    public void setJobcategory(String jobcategory) {
        this.jobcategory = jobcategory;
    }
}
