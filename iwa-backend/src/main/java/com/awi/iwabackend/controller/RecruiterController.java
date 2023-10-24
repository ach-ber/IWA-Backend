package com.awi.iwabackend.controller;

import com.awi.iwabackend.model.Recruiter;
import com.awi.iwabackend.repo.RecruiterRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterRepo repository;

    public RecruiterController(RecruiterRepo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Recruiter> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Recruiter findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Recruiter save(Recruiter recruiter) {
        return repository.save(recruiter);
    }

    @DeleteMapping
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
