package com.iwa.jobservice.matching;

import java.io.Serializable;

public class ReviewDTO implements Evaluation, Serializable {
    private Long id;
    private String title;
    private int rating;
    private String comment;
    private Long recruiterId;
    private String candidateId;
    private Long jobId;
    private Long createdAt;
    private Long jobcategory;

    public ReviewDTO() {
    }

    public ReviewDTO(String title, int rating, String comment, Long recruiterId, String candidateId, Long jobId, Long createdAt, Long jobcategory) {
        this.title = title;
        this.rating = rating;
        this.comment = comment;
        this.recruiterId = recruiterId;
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.createdAt = createdAt;
        this.jobcategory = jobcategory;
    }

    public ReviewDTO(ReviewDTO review, Long jobcategory) {
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

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
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

    // points calculés en fonction de la note donnée par l'employeur
    // x représente une review avec une note à 5 étoiles
    // y représente une review avec une note à 4 étoiles
    // z représente une review avec une note à 3 étoiles
    // -z représente une review avec une note à 2 étoiles
    // -y représente une review avec une note à 1 étoile
    // -x représente une review avec une note à 0 étoile
    // on suppose que
    // 1 review à 5 étoiles = 2 reviews à 4 étoiles = 6 reviews à 3 étoiles
    // ainsi, on a
    // 5x - 8y = 0
    // 4y - 6z = 0
    // x = 32/5, y = 4, z = 8/3
    // la valeur de la review compte double car celle-ci est plus importante que la valeur d'une opinion pour un candidat
    // car celle-ci est donnée par rapport à un job donné
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

    public Long getJobcategory() {
        return jobcategory;
    }

    public void setJobcategory(Long jobcategory) {
        this.jobcategory = jobcategory;
    }
}
