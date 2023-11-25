package com.iwa.recruiterservice.recruiter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/protected/recruiters")
public class ProtectedRecruiterController {
    private final RecruiterService service;

    public ProtectedRecruiterController(RecruiterService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/test")
    public String testProtected(){
        return "test protected";
    }

    @GetMapping("/admin/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String testProtectedAdmin(){
        return "test protected admin";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Recruiter> updateRecruiter(@PathVariable Long id, @RequestBody Recruiter updatedRecruiter) {
        return service.updateRecruiter(id, updatedRecruiter)
                .map(recruiter -> new ResponseEntity<>(recruiter, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('ADMIN')")
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
