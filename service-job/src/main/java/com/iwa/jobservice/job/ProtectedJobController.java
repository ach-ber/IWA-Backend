package com.iwa.jobservice.job;


import com.iwa.jobservice.matching.CandidateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/protected/jobs")
public class ProtectedJobController {

    private final JobService service;
    public ProtectedJobController(JobService service) {
        this.service = service;
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
    public ResponseEntity<List<CandidateDTO>> getMatchedCandidatesOrdered(@PathVariable Long id, @RequestHeader("X-User-Roles") String role) {
        Job job = service.getById(id).orElse(null);
        if (job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String city = service.getEstablishmentAddress(job.getEstablishment_key());
        if (city == null || city.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("role: " + role);
        switch (role) {
            case "ROLE_FREE":
                return service.getMatchedCandidatesOrdered(id, city)
                        .map(candidates -> ResponseEntity.status(HttpStatus.OK).body(candidates.stream().map(candidate -> convertToFreeUser(candidate)).collect(Collectors.toList()).subList(0, Math.min(5, candidates.size()))))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

            case "ROLE_SILVER":
                return service.getMatchedCandidatesOrdered(id, city)
                        .map(candidates -> ResponseEntity.status(HttpStatus.OK).body(candidates.stream().collect(Collectors.toList()).subList(0, Math.min(10, candidates.size()))))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            default:
                return service.getMatchedCandidatesOrdered(id, city)
                        .map(candidates -> ResponseEntity.status(HttpStatus.OK).body(candidates))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

}
