package com.iwa.jobservice.matching;

import java.util.ArrayList;
import java.util.List;

public class MatchingService {

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

    public List<Candidate> getMatchingCandidates(List<Candidate> candidates, JobMatch jobMatch) {
        List<Candidate> matchingCandidates = new ArrayList<>();

        for (Candidate candidate : candidates) {
            if (hasMatchingAvailability(candidate, jobMatch)) {
                matchingCandidates.add(candidate);
            }
        }
        return matchingCandidates;
    }

    public List<Evaluation> getCandidateEvaluationsByJobCategory(Candidate candidate, String jobCategory, List<Review> reviews) {
        List<Evaluation> evaluations = new ArrayList<>(candidate.getOpinions());

        for (Review review : reviews) {
            if (review.getJobcategory().equals(jobCategory)) {
                evaluations.add(new Review(review, jobCategory));
            }
        }
        return evaluations;
    }

    private boolean hasMatchingAvailability(Candidate candidate, JobMatch jobMatch) {
        for (Availability availability : candidate.getAvailabilities()) {
            if (availability.getJobcategory().equals(jobMatch.getJob().getCategory_key()) &&
                    isPeriodIncluded(availability.getStartsAt(), availability.getEndsAt(), jobMatch.getJob().getStartDate(), jobMatch.getJob().getEndDate()) &&
                    isCityIncluded(availability.getPlaces(), jobMatch.getEstablishment().getCity())) {
                return true;
            }
        }
        return false;
    }

    private boolean isPeriodIncluded(Long availabilityStartDate, Long availabilityEndDate, Long jobStartDate, Long jobEndDate) {
        return availabilityStartDate.compareTo(jobStartDate) >= 0 &&
                availabilityEndDate.compareTo(jobEndDate) <= 0;
    }

    private boolean isCityIncluded(List<String> places, String establishmentCity) {
        return places.contains(establishmentCity);
    }
}
