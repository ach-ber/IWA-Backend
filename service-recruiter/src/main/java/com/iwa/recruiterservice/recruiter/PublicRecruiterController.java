package com.iwa.recruiterservice.recruiter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/recruiters")
public class PublicRecruiterController {
    private final RecruiterService service;

    public PublicRecruiterController(RecruiterService service) {
        this.service = service;
    }

    @GetMapping
    public List<Recruiter> getAllRecruiters() {
        return service.getRecruiters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recruiter> getRecruiterById(@PathVariable Long id) {
        return service.getRecruiterById(id)
            .map(recruiter -> new ResponseEntity<>(recruiter, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/test")
    public String test(){return "test public";}
    @PostMapping
    public ResponseEntity<?> createRecruiter(@RequestBody RecruiterUserRequest newRecruiter) {
        return service.addRecruiterUser(newRecruiter);
    }
}
