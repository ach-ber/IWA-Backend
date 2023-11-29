package com.iwa.reviewservice.init;

import com.iwa.reviewservice.review.Review;
import com.iwa.reviewservice.review.ReviewService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ReviewService reviewService;

    public DataInitializer(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (reviewService.getNumberOfReviews() < 1) {
            reviewService.createReview(new Review("Super employé!", 5, "Super employé, très aimable", 1L, "065536c3-c644-712e-8000-6ec2863673a5", 1L, 1690332272L));
            reviewService.createReview(new Review("Pas fou le collègue", 2, "Pas très ponctuel le collègue", 1L, "065536c3-c644-712e-8000-6ec2863673a5", 2L, 1690332272L));
        }
    }
}
