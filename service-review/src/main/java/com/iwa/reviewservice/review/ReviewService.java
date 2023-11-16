package com.iwa.reviewservice.review;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
