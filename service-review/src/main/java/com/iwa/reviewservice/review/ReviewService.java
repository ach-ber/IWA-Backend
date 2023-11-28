package com.iwa.reviewservice.review;

import com.iwa.reviewservice.dto.CandidateDTO;
import com.iwa.reviewservice.dto.JobDTO;
import com.iwa.reviewservice.dto.RecruiterDTO;
import com.iwa.reviewservice.dto.ReviewDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Value("${candidates.api.url}")
    private String candidatesApiUrl;

    @Value("${jobs.api.url}")
    private String jobsApiUrl;

    @Value("${recruiters.api.url}")
    private String recruitersApiUrl;

    private final RestTemplate restTemplate;

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository, RestTemplate restTemplate) {
        this.reviewRepository = reviewRepository;
        this.restTemplate = restTemplate;
    }

    public Optional<CandidateDTO> getCandidateById(String id) {

        try {
            ResponseEntity<CandidateDTO> response = restTemplate.exchange(
                    candidatesApiUrl + "/candidates/" + id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<CandidateDTO>() {
                    }
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return Optional.ofNullable(response.getBody());
            } else {
                return Optional.empty();
            }
        } catch (HttpClientErrorException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<RecruiterDTO> getRecruiterById(Long id) {

        try {
            ResponseEntity<RecruiterDTO> response = restTemplate.exchange(
                    recruitersApiUrl + "/api/recruiters/" + id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<RecruiterDTO>() {
                    }
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return Optional.ofNullable(response.getBody());
            } else {
                return Optional.empty();
            }
        } catch (HttpClientErrorException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<JobDTO> getJobById(Long id) {

        try {
            ResponseEntity<JobDTO> response = restTemplate.exchange(
                    jobsApiUrl + "/api/jobs/" + id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<JobDTO>() {
                    }
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return Optional.ofNullable(response.getBody());
            } else {
                return Optional.empty();
            }
        } catch (HttpClientErrorException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    @Transactional
    public Review createReview(Review review) {
        if (review.getRating() > 5 || review.getRating() < 0) {
            return null;
        }
        return this.reviewRepository.save(review);
    }

    public Long getNumberOfReviews() {
        return this.reviewRepository.count();
    }

    public List<ReviewDTO> getReviews() {
        List<Review> reviews = this.reviewRepository.findAll();
        List<ReviewDTO> res = new ArrayList<>();
        if (reviews == null || reviews.isEmpty()) {
            return Collections.emptyList();
        } else {
            CandidateDTO candidate;
            JobDTO job;
            RecruiterDTO recruiter;
            for (Review review : reviews) {
                candidate = getCandidateById(review.getCandidateId()).orElse(null);
                job = getJobById(review.getJobId()).orElse(null);
                recruiter = getRecruiterById(review.getRecruiterId()).orElse(null);
                res.add(new ReviewDTO(review, candidate, job, recruiter));
            }
            return res;
        }
    }

    public Optional<ReviewDTO> getReviewById(Long id) {
        Review review = this.reviewRepository.findById(id).orElseGet(null);
        if (review == null) {
            return Optional.empty();
        }
        CandidateDTO candidate = getCandidateById(review.getCandidateId()).orElse(null);
        JobDTO job = getJobById(review.getJobId()).orElse(null);
        RecruiterDTO recruiter = getRecruiterById(review.getRecruiterId()).orElse(null);
        return Optional.of(new ReviewDTO(review, candidate, job, recruiter));
    }

    @Transactional
    public boolean deleteReviewById(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Review> updateReview(Long id, Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(id);

        if (existingReview.isPresent()) {
            updatedReview.setId(id);

            // partial updates
            if (updatedReview.getTitle() == null) {
                updatedReview.setTitle(existingReview.get().getTitle());
            }
            if (updatedReview.getRating() == null) {
                updatedReview.setRating(existingReview.get().getRating());
            }
            if (updatedReview.getComment() == null) {
                updatedReview.setComment(existingReview.get().getComment());
            }
            if (updatedReview.getRecruiterId() == null) {
                updatedReview.setRecruiterId(existingReview.get().getRecruiterId());
            }
            if (updatedReview.getCandidateId() == null) {
                updatedReview.setCandidateId(existingReview.get().getCandidateId());
            }
            if (updatedReview.getJobId() == null) {
                updatedReview.setJobId(existingReview.get().getJobId());
            }
            if (updatedReview.getCreatedAt() == null) {
                updatedReview.setCreatedAt(existingReview.get().getCreatedAt());
            }

            return Optional.of(reviewRepository.save(updatedReview));
        } else {
            return Optional.empty();
        }
    }

    public List<Review> getCandidateReviews(String candidateId) {
        try {

            // get the reviews from the repository which have the candidateId passed as
            // parameter
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

    public List<ReviewDTO> getRecruiterReviews(Long id) {
        return getReviews().stream()
                .filter(review -> review.getReview().getRecruiterId().equals(id))
                .collect(Collectors.toList());
    }
}
