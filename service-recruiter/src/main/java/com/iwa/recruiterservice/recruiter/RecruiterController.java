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
    public Recruiter getRecruiterById(@PathVariable Long id) {
        return service.getRecruiterById(id);
    }

    @PostMapping
    public ResponseEntity<Recruiter> createRecruiter(@RequestBody Recruiter newRecruiter) {
        Recruiter result = service.createRecruiter(newRecruiter);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recruiter> updateRecruiter(@PathVariable Long id, @RequestBody Recruiter updatedRecruiter) {
        Recruiter result = service.updateRecruiter(id, updatedRecruiter);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
