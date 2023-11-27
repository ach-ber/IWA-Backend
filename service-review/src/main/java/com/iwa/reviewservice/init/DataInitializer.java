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
    }
}
