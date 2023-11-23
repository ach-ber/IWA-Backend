package com.iwa.jobservice.matching;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchingService {
    public MatchingService() {
    }

    public Candidate setCandidateScore(Candidate candidate, Long jobCategory, List<ReviewDTO> reviews) {
        List<Evaluation> evaluations = getCandidateEvaluationsByJobCategory(candidate, jobCategory, reviews);
        double score = getEvaluationValue(evaluations);
        candidate.setScore(score);
        return candidate;
    }

    public double getEvaluationValue(List<Evaluation> evaluations) {
        double score = 0;
        if (evaluations == null || evaluations.isEmpty()) {
            return 4;
        }
        else {
            for (Evaluation evaluation : evaluations) {
                score += evaluation.getValue();
            }
            return score;
        }
    }

    public List<Candidate> getMatchingCandidates(List<Candidate> candidates, JobDTO job) {
        List<Candidate> matchingCandidates = new ArrayList<>();

        for (Candidate candidate : candidates) {
            if (hasMatchingAvailability(candidate, job)) {
                matchingCandidates.add(candidate);
            }
        }
        return matchingCandidates;
    }

    public List<Evaluation> getCandidateEvaluationsByJobCategory(Candidate candidate, Long jobCategory, List<ReviewDTO> reviews) {
        List<Evaluation> evaluations = new ArrayList<>(candidate.getOpinions());

        for (ReviewDTO review : reviews) {
            if (review.getJobcategory() == jobCategory) {
                evaluations.add(new ReviewDTO(review, jobCategory));
            }
        }
        return evaluations;
    }

    private boolean hasMatchingAvailability(Candidate candidate, JobDTO job) {
        for (AvailabilityDTO availability : candidate.getAvailabilities()) {
            if (
                    availability.getJobCategory() == job.getJob().getCategory_key()
                    // && isPeriodIncluded(availability.getStartsAt(), availability.getEndsAt(), job.getStartDate(), job.getEndDate())
                    // && isCityIncluded(availability.getPlaces(), job.getCity())
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean isPeriodIncluded(LocalDate availabilityStartDate, LocalDate availabilityEndDate, String jobStartDate, String jobEndDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDate jobStart = LocalDateTime.parse(jobStartDate, formatter).toLocalDate();
        LocalDate jobEnd = LocalDateTime.parse(jobEndDate, formatter).toLocalDate();

        return availabilityStartDate.compareTo(jobStart) <= 0 &&
                availabilityEndDate.compareTo(jobEnd) >= 0;
    }

    private boolean isCityIncluded(List<AddressDTO> places, String establishmentCity) {
        List<String> cities = new ArrayList<>();
        for (AddressDTO place : places) {
            cities.add(place.getCity());
        }
        return cities.contains(establishmentCity);
    }
}
