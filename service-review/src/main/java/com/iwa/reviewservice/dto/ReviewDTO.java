package com.iwa.reviewservice.dto;

import com.iwa.reviewservice.review.Review;

public class ReviewDTO {
    private Review review;
    private CandidateDTO candidate;

    public ReviewDTO() {
    }

    public ReviewDTO(Review review, CandidateDTO candidate) {
        this.review = review;
        this.candidate = candidate;
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
}
