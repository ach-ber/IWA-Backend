package com.iwa.reviewservice.review;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review createReview(Review review){
        if (review.getRating() > 5 || review.getRating() < 0) {
            return null;
        }
        return this.reviewRepository.save(review);
    }

    public Long getNumberOfReviews(){
        return this.reviewRepository.count();
    }

    public List<Review> getReviews(){
        return this.reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id){
        return this.reviewRepository.findById(id);
    }

    @Transactional
    public boolean deleteReviewById(Long id){
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Review> updateReview(Long id, Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(id);

        if (existingReview != null) {
            updatedReview.setId(id);
            return Optional.of(reviewRepository.save(updatedReview));
        } else {
            return null;
        }
    }

    public List<Review> getCandidateReviews(String candidateId) {
        try {

            // get the reviews from the repository which have the candidateId passed as parameter
            List<Review> reviews = this.reviewRepository.findAll();
            if (reviews == null || reviews.isEmpty()) {
                return Collections.emptyList();
            }

            List<Review> res = reviews.stream()
                    .filter(review -> review.getCandidateId().equals(candidateId))
                    .collect(Collectors.toList());

            return res;
        } catch (IllegalArgumentException e) {
            // Handle the case where the candidateId is not a valid UUID
            // For example, log an error or return an empty list
            return Collections.emptyList();
        }
    }
}
