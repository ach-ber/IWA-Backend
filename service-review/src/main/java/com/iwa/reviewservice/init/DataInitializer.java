package com.iwa.reviewservice.init;

import com.iwa.reviewservice.review.Review;
import com.iwa.reviewservice.review.ReviewService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ReviewService reviewService;

    public DataInitializer(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (reviewService.getNumberOfReviews() == 0) {
            reviewService.createReview(new Review("Review 1", 10, "Perfect!", 1L, 1L, 1L, 3456767L));
        }
    }
}
