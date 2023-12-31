package com.iwa.jobservice.job;


import com.iwa.jobservice.matching.CandidateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public/jobs")
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


}
