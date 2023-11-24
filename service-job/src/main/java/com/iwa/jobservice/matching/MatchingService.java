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

    // param: la liste des candidats qui ont matché avec le job, la catégorie du job, la liste de tous les reviews
    // retour: le candidat avec le score calculé en fonnction des notes de ses reviews
    public Candidate setCandidateScore(Candidate candidate, Long jobCategory, List<ReviewDTO> reviews) {
        List<Evaluation> evaluations = getCandidateEvaluationsByJobCategory(candidate, jobCategory, reviews);
        double score = getEvaluationValue(evaluations);
        candidate.setScore(score);
        return candidate;
    }

    // param: la liste des reviews et opinions d'un candidat
    // si le candidat n'a ni review ni opinion, on lui attribue la note 4
    // sinon on ajoute le score associé à chaque review et opinion, review dans un job qui a la même catégorie que le job recherché
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

    // param: la liste de tous les candidats, le job recherché
    // retour: la liste des candidats qui ont matché avec le job
    public List<Candidate> getMatchingCandidates(List<Candidate> candidates, JobDTO job) {
        List<Candidate> matchingCandidates = new ArrayList<>();

        for (Candidate candidate : candidates) {
            if (hasMatchingAvailability(candidate, job)) {
                matchingCandidates.add(candidate);
            }
        }
        return matchingCandidates;
    }

    // param: le candidat, la catégorie du job recherché, la liste de toutes les reviews
    // retour: la liste des reviews, qui ont été faits sur le candidat dans la même catégorie que le job recherché, et opinions du candidat
    public List<Evaluation> getCandidateEvaluationsByJobCategory(Candidate candidate, Long jobCategory, List<ReviewDTO> reviews) {
        List<Evaluation> evaluations = new ArrayList<>(candidate.getOpinions());

        for (ReviewDTO review : reviews) {
            if (review.getJobcategory() == jobCategory) {
                evaluations.add(new ReviewDTO(review, jobCategory));
            }
        }
        return evaluations;
    }

    // param: le candidat, le job recherché
    // retour: true si le candidat a une disponibilité qui matche avec le job
    private boolean hasMatchingAvailability(Candidate candidate, JobDTO job) {
        for (AvailabilityDTO availability : candidate.getAvailabilities()) {
            if (
                    availability.getJobCategory() == job.getJob().getCategory_key() &&
                    isPeriodIncluded(availability.getStartsAt(), availability.getEndsAt(), job.getStartDate(), job.getEndDate()) &&
                    isCityIncluded(availability.getPlaces(), job.getCity())
            ) {
                return true;
            }
        }
        return false;
    }

    // param: la date de début et de fin de disponibilité du candidat, la date de début et de fin du job
    // retour: true si la période du job est incluse dans la période de disponibilité du candidat
    private boolean isPeriodIncluded(LocalDate availabilityStartDate, LocalDate availabilityEndDate, String jobStartDate, String jobEndDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDate jobStart = LocalDateTime.parse(jobStartDate, formatter).toLocalDate();
        LocalDate jobEnd = LocalDateTime.parse(jobEndDate, formatter).toLocalDate();

        return availabilityStartDate.compareTo(jobStart) <= 0 &&
                availabilityEndDate.compareTo(jobEnd) >= 0;
    }

    // param: la liste des villes où le candidat est disponible, la ville du job
    // retour: true si la ville du job est incluse dans la liste des villes où le candidat est disponible
    private boolean isCityIncluded(List<AddressDTO> places, String establishmentCity) {
        List<String> cities = new ArrayList<>();
        for (AddressDTO place : places) {
            cities.add(place.getCity());
        }
        return cities.contains(establishmentCity);
    }
}
