package com.iwa.jobservice.job;


import com.iwa.jobservice.matching.Candidate;
import com.iwa.jobservice.matching.CandidateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService service;
    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return service.getJobs();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {
        return service.getById(id)
                .map(job -> ResponseEntity.status(HttpStatus.OK).body(job))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestBody final Job job) {
        return service.createJob(job);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Job> delete(@PathVariable Long id) {
        boolean deleted = service.deleteById(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        return service.updateJob(id, updatedJob)
            .map(job -> ResponseEntity.status(HttpStatus.OK).body(job))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public CandidateDTO convertToFreeUser(CandidateDTO candidateDTO) {
        candidateDTO.setPhone(null);
        candidateDTO.setEmail(null);
        return candidateDTO;
    }
    @GetMapping("/matchedcandidates/{id}")
    public ResponseEntity<List<CandidateDTO>> getMatchedCandidatesOrdered(@PathVariable Long id, @RequestParam(required = false) String role) {
        Job job = service.getById(id).orElse(null);
        if (job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String city = service.getEstablishmentAddress(job.getEstablishment_key());
        if (city == null || city.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        switch (role) {
            case "ROLE_FREE":
                return service.getMatchedCandidatesOrdered(id, city)
                        .map(candidates -> ResponseEntity.status(HttpStatus.OK).body(candidates.stream().map(candidate -> convertToFreeUser(candidate)).collect(Collectors.toList()).subList(0, 5)))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

            case "ROLE_SILVER":
                return service.getMatchedCandidatesOrdered(id, city)
                        .map(candidates -> ResponseEntity.status(HttpStatus.OK).body(candidates.stream().collect(Collectors.toList()).subList(0, 10)))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            default:
                return service.getMatchedCandidatesOrdered(id, city)
                        .map(candidates -> ResponseEntity.status(HttpStatus.OK).body(candidates))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }


    @GetMapping("/candidates")
    public List<CandidateDTO> getAllCandidates() {
        return service.getAllCandidates();
    }

}
