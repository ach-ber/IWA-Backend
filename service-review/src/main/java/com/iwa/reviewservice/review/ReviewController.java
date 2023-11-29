package com.iwa.reviewservice.review;

import com.iwa.reviewservice.dto.ReviewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        return service.getReviews()
            .map(reviews -> new ResponseEntity<>(reviews, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        return service.getReviewById(id)
            .map(review -> new ResponseEntity<>(review, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/recruiter/{id}")
    public ResponseEntity<List<ReviewDTO>> getRecruiterReviews(@PathVariable Long id) {
        List<ReviewDTO> reviews = service.getRecruiterReviews(id);
        if (reviews != null) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review newReview) {
        if (newReview.getRating() > 5 || newReview.getRating() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Review result = service.createReview(newReview);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        return service.updateReview(id, updatedReview)
            .map(review -> new ResponseEntity<>(review, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        boolean deleted = service.deleteReviewById(id);

        if (deleted) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/candidate/{candidateId}")
    public List<Review> getCandidateReviews(@PathVariable String candidateId) {
        return service.getCandidateReviews(candidateId);
    }
}
