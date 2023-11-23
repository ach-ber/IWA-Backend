package com.iwa.recruiterservice.recruiter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService service;

    public RecruiterController(RecruiterService service) {
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
    public String test(){
        return service.test();
    }
    @PostMapping
    public ResponseEntity<?> createRecruiter(@RequestBody RecruiterUserRequest newRecruiter) {
        return service.createRecruiter(newRecruiter);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recruiter> updateRecruiter(@PathVariable Long id, @RequestBody Recruiter updatedRecruiter) {
        return service.updateRecruiter(id, updatedRecruiter)
            .map(recruiter -> new ResponseEntity<>(recruiter, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecruiter(@PathVariable Long id) {
        boolean deleted = service.deleteRecruiterById(id);

        if (deleted) {
            return new ResponseEntity<>("Recruiter deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Recruiter not found", HttpStatus.NOT_FOUND);
        }
    }
}
