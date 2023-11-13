package com.iwa.recruiterservice.recruiter;

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
}
