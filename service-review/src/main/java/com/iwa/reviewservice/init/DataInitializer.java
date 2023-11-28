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
        if (reviewService.getNumberOfReviews() < 2) {
            reviewService.createReview(new Review("Good Experience", 5, "The candidate was good", 1l, "065536c3-c650-7b98-8003-2105da03432a", 1l, 1679817600000l));
            reviewService.createReview(new Review("Bad Experience", 1, "The candidate was bad", 1l, "065536c3-c650-7b98-8003-2105da03432a", 1l, 1679817600000l));
        }
    }
}
