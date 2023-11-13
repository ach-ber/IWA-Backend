package com.iwa.jobservice.job;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

}
