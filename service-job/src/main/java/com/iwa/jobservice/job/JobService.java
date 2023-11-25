package com.iwa.jobservice.job;

import com.iwa.jobservice.jobcategory.JobcategoryService;
import com.iwa.jobservice.matching.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class JobService {
    @Value("${candidates.api.url}")
    private String candidatesApiUrl;

    @Value("${recruiter.api.url}")
    private String recruiterApiUrl;

    @Value("${review.api.url}")
    private String reviewApiUrl;

    private final RestTemplate restTemplate;
    private JobRepository jobRepository;

    private JobcategoryService jobcategoryService;

    private MatchingService matchingService;

    public JobService(JobRepository jobRepository, RestTemplate restTemplate, JobcategoryService jobcategoryService, MatchingService matchingService) {
        this.restTemplate = restTemplate;
        this.jobRepository = jobRepository;
        this.jobcategoryService = jobcategoryService;
        this.matchingService = matchingService;
    }

    public List<CandidateDTO> getAllCandidates() {
        ResponseEntity<List<CandidateDTO>> response = restTemplate.exchange(
                candidatesApiUrl + "/candidates",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CandidateDTO>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return Collections.emptyList();
        }
    }

    public EstablishmentDTO getEstablishmentById(Long id) {
        ResponseEntity<EstablishmentDTO> response = restTemplate.exchange(
                recruiterApiUrl + "/api/establishments/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EstablishmentDTO>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public String getEstablishmentAddress(Long establishmentAddressId) {
        ResponseEntity<String> response = restTemplate.exchange(
                recruiterApiUrl + "/api/addresses/city/" + establishmentAddressId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public List<ReviewDTO> getCandidateReview(String candidateId) {
        ResponseEntity<List<ReviewDTO>> response = restTemplate.exchange(
                reviewApiUrl + "/api/reviews/candidate/" + candidateId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReviewDTO>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return Collections.emptyList();
        }
    }

    public List<Candidate> getMatchingCandidates(List<Candidate> candidates, JobDTO job) {
        return matchingService.getMatchingCandidates(candidates, job);
    }

    public Optional<List<Candidate>> getMatchedCandidatesOrdered(Long id, String city) {
        Optional<Job> job = getById(id);
        if (job == null) {
            return null;
        }
        else {
            List<CandidateDTO> candidatesDto = getAllCandidates();
            List<Candidate> candidates = new ArrayList<>();
            for (CandidateDTO candidateDto : candidatesDto) {
                candidates.add(new Candidate(candidateDto, 0));
            }
            List<Candidate> matchingCandidates = getMatchingCandidates(candidates, new JobDTO(job.get(), city));
            for (Candidate candidate : matchingCandidates) {
                matchingService.setCandidateScore(candidate, job.get().getCategory_key(), getCandidateReview(candidate.getId()));
            }
            matchingCandidates.sort((c1, c2) -> Double.compare(c2.getScore(), c1.getScore()));
            return Optional.of(matchingCandidates);
        }
    }

    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Transactional
    public Job createJob(Job job) {
        jobRepository.save(job);
        return job;
    }

    @Transactional
    public List<Job> createJob(List<Job> list) {
        jobRepository.saveAll(list);
        return list;
    }

    public Long getNumberOfJobs() {
        return jobRepository.count();
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Job> updateJob(Long id, Job updatedJob) {
        Optional<Job> existingJob = jobRepository.findById(id);

        if (existingJob != null) {
            updatedJob.setId(id);
            return Optional.of(jobRepository.save(updatedJob));
        } else {
            return null;
        }
    }

    public Optional<Job> getById(Long id) {
        return jobRepository.findById(id);
    }
}
