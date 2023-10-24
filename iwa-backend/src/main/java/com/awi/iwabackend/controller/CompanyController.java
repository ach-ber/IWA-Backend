package com.awi.iwabackend.controller;

import com.awi.iwabackend.model.Company;
import com.awi.iwabackend.repo.CompanyRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyRepo repository;

    public CompanyController(CompanyRepo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Company> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Company save(Company company) {
        return repository.save(company);
    }

    @DeleteMapping
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
